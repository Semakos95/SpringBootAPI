package gr.cinema.api.jsp.controller;

import gr.cinema.api.dto.RentDTO;
import gr.cinema.api.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RentJspController {

    private RentService rentService;
    @Autowired
    public RentJspController(RentService rentService) {
        this.rentService = rentService;
    }


    @RequestMapping(value = "/rentRoom", method = RequestMethod.GET)//http://localhost:8080/rentRoom
    public String showRentRoomForm(Model model){
        RentDTO rentDTO= new RentDTO();
        model.addAttribute("rent",rentDTO);
        return "createRent";
    }
    @PostMapping("/rentRoom")
    public String rentRoom(@ModelAttribute("rent")RentDTO rentDTO) throws Exception{
        rentService.insertRentDTO(rentDTO);
        return "redirect:/showAllRoomRents";
    }
    @GetMapping("/showAllRoomRents")
    public String listOfRents(Model model){
        List<RentDTO> rentDTOList =rentService.getAllRentsDTOList();
        model.addAttribute("allRents",rentDTOList);
        return "rents";
    }


}
