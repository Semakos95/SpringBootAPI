package gr.cinema.api.controller;

import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.validation.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    private final EmailValidationService emailValidationService;
    @Autowired
    public ValidationController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    @PostMapping("/simple")//http://localhost:8080/validation/simple
    public ResponseEntity<String> validateUsingSimpleRegex(@RequestBody UserDTO userDTO) {

        if (emailValidationService.testUsingSimpleRegex(userDTO)) {
            return ResponseEntity.ok("Email is valid according to simple regex.");
        } else {
            return ResponseEntity.badRequest().body("Email is not valid.");
        }
    }

    @PostMapping("/strict")//http://localhost:8080/validation/strict
    public ResponseEntity<String> validateUsingStrictRegex(@RequestBody UserDTO userDTO) {

        if (emailValidationService.testUsingStrictRegex(userDTO)) {
            return ResponseEntity.ok("Email is valid according to strict regex.");
        } else {
            return ResponseEntity.badRequest().body("Email is not valid.");
        }
    }


}
