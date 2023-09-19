package gr.cinema.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.entity.Role;
import gr.cinema.api.entity.User;
import gr.cinema.api.entity.Performance;
import gr.cinema.api.exception.BadRequestException;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.RoleRepository;
import gr.cinema.api.repository.UserRepository;
import gr.cinema.api.validation.EmailValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidationService emailValidationService;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, EmailValidationService emailValidationService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailValidationService = emailValidationService;
    }

    public List<UserDTO> getUsersByNameDTOList(String name) {
        LOGGER.info("getUsersByNameDTOList()");

        final List<User> userList = userRepository.findByFirstName(name);
        final List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            final UserDTO userDTO = toUserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public List<UserDTO> getAllUserDTOList() {
        LOGGER.info("getAllUserDTOList()");

        final List<User> userList = userRepository.findAll();
        final List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            final UserDTO userDTO = toUserDTO(user);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    public User getUser(Long id) {
        LOGGER.info("getUser() with 'ID': {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO getUserDTO(Long id) {
        LOGGER.info("getUserDTO() with 'ID': {}", id);
        final User user = getUser(id);

        if (user == null) {
            LOGGER.error("getUserDTO() User with 'ID': {} Does not exist!", id);
            throw new NotFoundException();
        }
        return toUserDTO(user);
    }

    public void loginUser(UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOGGER.info("User signed success!!");

    }

    public UserDTO insertUserDTO(UserDTO userDTO) {
        if (userDTO.getId() != null) {
             LOGGER.error("insertUserDTO(): there is a body 'ID': {}", userDTO.getId());
             throw new BadRequestException();
        }
        else if (userRepository.existsByUsername(userDTO.getUsername())){
            LOGGER.error("insertUserDTO(): This Username: '{}' is taken! ", userDTO.getUsername());
            throw new BadRequestException();
        }
        // Validate email using simple regex
        if (!emailValidationService.testUsingSimpleRegex(userDTO)) {
            LOGGER.error("insertUserDTO(): Invalid email address: '{}'", userDTO.getEmail());
            throw new BadRequestException();
        }

        User user = new User();
        toUser(userDTO, user);

        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);
        LOGGER.info("insertUserDTO: You inserted successfully new user to: {}", userDTO);
        LOGGER.info("insertUserDTO: Also has a '{}' Role ",role.getName());

        return toUserDTO(user);
    }

    public UserDTO updateUserDTO(UserDTO userDTO) throws Exception {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(Exception::new);//not found exception

        toUser(userDTO, user);
        user = userRepository.save(user);
        LOGGER.info("updateUserDTO() You updated successfully user to: {}", userDTO);

        return toUserDTO(user);
    }

    public void deleteUser(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            LOGGER.error("deleteUser(): User with 'ID' {} does not exist.", id);
            throw new Exception("NotFound");
        }
        userRepository.deleteById(id);
        LOGGER.info("deleteUser(): You deleted successfully user with 'ID': {}", id);
    }

    private UserDTO toUserDTO(User user) {
        final UserDTO userDTO = new UserDTO();
        final List<PerformanceDTO> performanceDTOList = new ArrayList<>();

        if (user.getPerformances() != null) {
            for (Performance performance : user.getPerformances()) {
                final PerformanceDTO performanceDTO = new PerformanceDTO();
                performanceDTO.setId(performance.getId());
                performanceDTO.setActors(performance.getActors());
                performanceDTOList.add(performanceDTO);
            }
        }
        userDTO.setPerformancesDTO(performanceDTOList);

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setPostalCode(user.getPostalCode());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    private void toUser(UserDTO userDTO, User user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setPostalCode(userDTO.getPostalCode());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
    }

}
