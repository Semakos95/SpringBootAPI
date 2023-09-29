package gr.cinema.api.jsp.controller;

import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.jsp.service.JspService;
import gr.cinema.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JspController {

    private JspService jspService;

    private UserService userService;
    @Autowired
    public JspController(JspService jspService,UserService userService) {
        this.jspService=jspService;
        this.userService=userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)//http://localhost:8080/login OK
    public String showLoginPage(ModelMap model){
        return "auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)//http://localhost:8080/register OK
    public String showRegisterUserForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "createUser";
    }

    @PostMapping("/register")//http://localhost:8080/register
    public String registerUser(@ModelAttribute("user") UserDTO userDTO) {
        userService.registerUserDTO(userDTO);
        // You can add success messages or redirection logic here
        return "redirect:/showAllUsers";
    }

    @GetMapping("/showAllUsers")//http://localhost:8080/allUsers OK
    public String showRegisteredUsers(Model model) {
        List<UserDTO> userDTOList = userService.getAllUserDTOList();
        model.addAttribute("users", userDTOList);
        return "users";
    }

    @GetMapping("/admin")//http://localhost:8080/admin OK
    public String welcome(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "welcome";
    }
}
