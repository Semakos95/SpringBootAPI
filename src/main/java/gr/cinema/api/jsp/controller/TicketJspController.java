package gr.cinema.api.jsp.controller;

import gr.cinema.api.dto.TicketDTO;
import gr.cinema.api.service.TicketService;
import gr.cinema.api.utils.CustomLocalDateEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TicketJspController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketJspController.class);

    private TicketService ticketService;
    @Autowired
    public TicketJspController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new CustomLocalDateEditor());
    }

    @RequestMapping(value = "/createTicket",method = RequestMethod.GET)
    public String showCreateTicketForm(Model model){
        TicketDTO ticketDTO = new TicketDTO();
        model.addAttribute("ticket",ticketDTO);
        return "createTicket";
    }

    @PostMapping("/createTicket")
    public String createTicket(@ModelAttribute("ticket")TicketDTO ticketDTO)throws Exception{
        ticketService.insertTicketDTO(ticketDTO);
        return "redirect:/showAllTickets";
    }

    @GetMapping("/editTicket/{id}")
    public String showEditTicketForm(@PathVariable ("id")Long id, Model model) {
        TicketDTO ticketDTO = ticketService.getTicketDTO(id);
        model.addAttribute("ticket", ticketDTO);
        return "editTicket";
    }

    @PostMapping("/editTicket/{id}")
    public String updateTicket(@ModelAttribute("ticket") TicketDTO ticketDTO,@PathVariable ("id")Long id) throws Exception {
        ticketDTO.setId(id);
        if (!id.equals(ticketDTO.getId())) {
            LOGGER.error("updateTicket(): path variable 'id': {} and body 'id': {} does not match", id, ticketDTO.getId());
            throw new Exception("BadRequest");
        }
        ticketService.updateTicketDTO(ticketDTO);
        return "redirect:/showAllTickets";
    }


    @GetMapping("/showAllTickets")
    public String listOfTickets(Model model){
        List<TicketDTO>ticketDTOList = ticketService.getAllTicketDTOList();
        model.addAttribute("allTickets", ticketDTOList);
        return "tickets";
    }



}
