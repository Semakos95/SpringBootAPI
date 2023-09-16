package gr.cinema.api.testing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dream")
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World,ONE DAY im gonna escape from delivery, AND i promise you... IM GONNA BE DEVELOPER ";
    }
}