package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.TicketDTO;
import gr.cinema.api.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TicketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    //http://localhost:8080/api/ticket/getSpecificTickets?performanceId=1&roomId=1&sectionId=1&date=2022-05-05 OK
    @GetMapping(WebConstants.TICKET_CONTROLLER_SPECIFIC_TICKETS)
    public List<TicketDTO> getSpecificTicketsList(@RequestParam("performanceId")Long performanceId, @RequestParam("roomId")Long roomId,
                                                  @RequestParam("sectionId")Long sectionId, @RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date){
        return ticketService.getSpecificTicketsDTOList(performanceId,roomId,sectionId,date);
    }
    @GetMapping(WebConstants.TICKET_CONTROLLER_GET_ALL) //http://localhost:8080/api/ticket/getAll OK
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTicketDTOList();
    }

    @GetMapping(WebConstants.TICKET_CONTROLLER_GET_TICKET) //http://localhost:8080/api/ticket/getTicket/1 OK
    public TicketDTO getTicket(@PathVariable(WebConstants.ID) Long id) {
        return ticketService.getTicketDTO(id);
    }

    @PostMapping(WebConstants.TICKET_CONTROLLER_INSERT) //http://localhost:8080/api/ticket/insert OK
    public TicketDTO insertTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.insertTicketDTO(ticketDTO);
    }

    @PutMapping(WebConstants.TICKET_CONTROLLER_UPDATE) //http://localhost:8080/api/ticket/update/5 OK
    public TicketDTO updateTicket(@PathVariable(WebConstants.ID) Long id, @RequestBody TicketDTO ticketDTO) throws Exception {
        if (!id.equals(ticketDTO.getId())) {
            LOGGER.error("updateTicket(): path variable 'id': {} and body 'id': {} does not match", id, ticketDTO.getId());
            throw new Exception("BadRequest");
        }
        ticketDTO.setId(id);
        return ticketService.updateTicketDTO(ticketDTO);
    }

    @DeleteMapping(WebConstants.TICKET_CONTROLLER_DELETE)//http://localhost:8080/api/ticket/delete/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable(WebConstants.ID) Long id) throws Exception {
        ticketService.deleteTicketDTO(id);
    }
}
