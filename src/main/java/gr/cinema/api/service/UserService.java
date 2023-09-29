package gr.cinema.api.service;

import java.util.*;
import java.util.stream.Collectors;

import gr.cinema.api.config.jwt.JwtUtils;
import gr.cinema.api.config.payload.response.JwtResponse;
import gr.cinema.api.config.services.UserDetailsImpl;
import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.entity.ERole;
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
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidationService emailValidationService;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, EmailValidationService emailValidationService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
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

    public JwtResponse loginUserWithJwtResponse(UserDTO userDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Create and return a JwtResponse instance with the authentication result
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);

        return jwtResponse;
    }

    public UserDTO registerUserDTO(UserDTO userDTO) {
        if (userDTO.getId() != null) {
            LOGGER.error("registerUserDTO(): there is a body 'id': {}", userDTO.getId());
           throw new BadRequestException();
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            LOGGER.error("registerUserDTO(): Error: Username: {} is already taken!", userDTO.getUsername());
           throw new BadRequestException();
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            LOGGER.error("registerUserDTO(): Error: Email: {} is already in use!", userDTO.getEmail());
          throw new BadRequestException();
        }
        if (!emailValidationService.testUsingSimpleRegex(userDTO)) {
            LOGGER.error("registerUserDTO(): Invalid email address: '{}'", userDTO.getEmail());
           throw new BadRequestException();
        }

        User user = new User();
        toUser(userDTO, user);

        Set<String> strRoles = userDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            for (String role : strRoles) {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            }
        }

        user.setRoles(roles);

        user = userRepository.save(user);
        LOGGER.info("registerUserDTO: User registered successfully! {}", userDTO);

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

        // Populate roles
        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName().toString())
                .collect(Collectors.toSet());
        userDTO.setRoles(roleNames);

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
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }

}
