package gr.cinema.api.controller.auth;

import gr.cinema.api.config.payload.response.JwtResponse;
import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(WebConstants.AUTH)
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/loginUser") //http://localhost:8080/api/auth/loginUser OK
    public JwtResponse loginUser(@RequestBody UserDTO userDTO) {
        return userService.loginUserWithJwtResponse(userDTO);
    }

    @PostMapping("/registerUser") //http://localhost:8080/api/auth/registerUser OK
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUserDTO(userDTO);
    }


}
