package gr.cinema.api.jsp.service;

import org.springframework.stereotype.Service;

@Service
public class JspService {

    public boolean validUser(String username, String password){

        return username.equalsIgnoreCase("giwrgos") && password.equalsIgnoreCase("semaniakou");

    }
}
