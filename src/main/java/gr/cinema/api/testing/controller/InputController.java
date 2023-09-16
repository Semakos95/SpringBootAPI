package gr.cinema.api.testing.controller;

import gr.cinema.api.dto.inputDTO.InputDTO;
import gr.cinema.api.testing.dto.ReqBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class InputController {
    private final InputDTO inputDTO;

    @Autowired
    public InputController(InputDTO inputDTO) {
        this.inputDTO = inputDTO;
    }

    @PostMapping("/print/{firstString}")
    public String printData(@PathVariable String firstString, @RequestParam("secondString") String secondString, @RequestBody ReqBodyDTO reqBody) {
        StringBuilder response = new StringBuilder();

        response.append(firstString)
                .append(" ")
                .append(secondString)
                .append(" ")
                .append(reqBody.getThirdString())
                .append("\n");

        if (reqBody.isAddMe()) {
            int sum = reqBody.getFirstInt() + reqBody.getSecondInt();

            response.append("The sum of numbers: ")
                    .append(reqBody.getFirstInt())
                    .append("+")
                    .append(reqBody.getSecondInt())
                    .append("=")
                    .append(sum);
        } else {
            response.append("The given numbers was: ")
                    .append(reqBody.getFirstInt())
                    .append(" and ")
                    .append(reqBody.getSecondInt())
                    .append(".");
        }


        return response.toString();
    }

}
