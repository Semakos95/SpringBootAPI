package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.OnlineTicketDTO;
import gr.cinema.api.repository.OnlineTicketRepository;
import gr.cinema.api.service.OnlineTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OnlineTicketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineTicketController.class);
    private final OnlineTicketService onlineTicketService;
    @Autowired
    public OnlineTicketController(OnlineTicketService onlineTicketService){
        this.onlineTicketService=onlineTicketService;
    }
    @GetMapping(WebConstants.ONLINE_TICKET_CONTROLLER_GET_ALL)//http://localhost:8080/api/onlineTicket/allOnlineTickets OK
    public List<OnlineTicketDTO> getAllOnlineTickets(){
        return onlineTicketService.getAllOnlineTicketDTOList();
    }
    @GetMapping(WebConstants.ONLINE_TICKET_CONTROLLER_GET_ONLINE_TICKET) //http://localhost:8080/api/onlineTicket/getTicket/1 OK
    public OnlineTicketDTO getOnlineTicket(@PathVariable ("id")Long id){
        return onlineTicketService.getOnlineTicketDTO(id);
    }
    @PostMapping(WebConstants.ONLINE_TICKET_CONTROLLER_INSERT) //http://localhost:8080/api/onlineTicket/insert OK
    public OnlineTicketDTO insertOnlineTicket(@RequestBody OnlineTicketDTO onlineTicketDTO){
        return onlineTicketService.insertOnlineTicketDTO(onlineTicketDTO);
    }
    @PutMapping(WebConstants.ONLINE_TICKET_CONTROLLER_UPDATE) //http://localhost:8080/api/onlineTicket/update/1 OK (insert and id on json body("id": 1))
    public OnlineTicketDTO updateOnlineTicket(@PathVariable ("id")Long id , @RequestBody OnlineTicketDTO onlineTicketDTO)throws Exception{
       if (!id.equals(onlineTicketDTO.getId())){
           LOGGER.error("updateOnlineTicket(): path variable 'id': {} and body 'id': {} does not match", id, onlineTicketDTO.getId());
           throw new Exception("BadRequest");
       }
       onlineTicketDTO.setId(id);
       return onlineTicketService.updateOnlineTicketDTO(onlineTicketDTO);
    }
    @DeleteMapping(WebConstants.ONLINE_TICKET_CONTROLLER_DELETE) //http://localhost:8080/api/onlineTicket/delete/1 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOnlineTicket(@PathVariable ("id")Long id)throws Exception{
        onlineTicketService.deleteOnlineTicket(id);
    }
}
