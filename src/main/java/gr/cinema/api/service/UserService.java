package gr.cinema.api.service;

import java.util.ArrayList;
import java.util.List;

import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.entity.User;
import gr.cinema.api.entity.Performance;
import gr.cinema.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User getUser(Long id) {
        LOGGER.info("getCustomer() with id: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO getUserDTO(Long id) {
        LOGGER.info("getCustomerDTO() with id: {}", id);
        final User user = getUser(id);

        if (user == null) {
            //   throw new NotFoundException();
        }
        return toUserDTO(user);
    }

    public List<UserDTO> getUsersByNameDTOList(String name) {
        LOGGER.info("getCustomersByName()");

        final List<User> userList = userRepository.findByFirstName(name);
        final List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            final UserDTO userDTO = toUserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    //todo: fix all exceptions

    public List<UserDTO> getAllUserDTOList() {
        LOGGER.info("getAllCustomers()");

        final List<User> userList = userRepository.findAll();
        final List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            final UserDTO userDTO = toUserDTO(user);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    public UserDTO insertUserDTO(UserDTO userDTO) {
        if (userDTO.getId() != null) {
            LOGGER.error("insertCustomerDTO(): there is a body 'id': {}", userDTO.getId());
            // throw new BadRequestException();
        }

        User user = new User();
        toUser(userDTO, user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);
        LOGGER.info("insertCustomerDTO: {}", userDTO);

        return toUserDTO(user);
    }

    public UserDTO updateUserDTO(UserDTO userDTO) throws Exception {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(Exception::new);//not found exception

        toUser(userDTO, user);
        user = userRepository.save(user);
        LOGGER.info("updateCustomerDTO() update to: {}", userDTO);

        return toUserDTO(user);
    }

    public void deleteUser(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            LOGGER.error("deleteCustomer(): customer with id {} does not exist.", id);
            throw new Exception("NotFound");
        }
        userRepository.deleteById(id);
        LOGGER.info("deleteCustomer() with id: {}", id);
    }

    public UserDTO getByEmailAndPasswordDTO(String email, String password) {
        LOGGER.info("getByEmailAndPasswordDTO()");
        final User user = userRepository.findByEmailAndPassword(email, password);

        return toUserDTO(user);
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
