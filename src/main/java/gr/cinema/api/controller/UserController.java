package gr.cinema.api.controller;

import java.util.List;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebConstants.USER)
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(WebConstants.GET_ALL_USERS_BY_NAME) // http://localhost:8080/api/user/allUsersByName?firstName=george OK
    public List<UserDTO> getUsersByName(@RequestParam("firstName") String name) {
        return userService.getUsersByNameDTOList(name);
    }

    @GetMapping(WebConstants.GET_ALL_USERS) //http://localhost:8080/api/user/allUsers OK
    public List<UserDTO> getAllUsers() {
        return userService.getAllUserDTOList();
    }

    @GetMapping(WebConstants.ID_PATH) //http://localhost:8080/api/user/1 OK
    public UserDTO getUser(@PathVariable(WebConstants.ID) Long id) {
        return userService.getUserDTO(id);
    }


    @PutMapping(WebConstants.ID_PATH)//http://localhost:8080/api/user/1 OK (insert same id on json body with same id on url path("id": 1))
    public UserDTO updateUser(@PathVariable(WebConstants.ID) Long id, @RequestBody UserDTO userDTO) throws Exception {
        if (!id.equals(userDTO.getId())) {
            LOGGER.error("updateUser(): path variable 'id': {} and body 'id': {} does not match", id, userDTO.getId());
            throw new Exception("BadRequest");
        }
        userDTO.setId(id);
        return userService.updateUserDTO(userDTO);
    }

    @DeleteMapping(WebConstants.ID_PATH) // http://localhost:8080/api/user/2 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(WebConstants.ID) Long id) throws Exception {
        userService.deleteUser(id);
    }


}

