package gr.cinema.api.service;

import gr.cinema.api.dto.OnlineTicketDTO;
import gr.cinema.api.dto.TicketDTO;
import gr.cinema.api.entity.OnlineTicket;
import gr.cinema.api.entity.Ticket;
import gr.cinema.api.repository.OnlineTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineTicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineTicketService.class);
    private final OnlineTicketRepository onlineTicketRepository;
    private final TicketService ticketService;

    @Autowired
    public OnlineTicketService(OnlineTicketRepository onlineTicketRepository, TicketService ticketService) {
        this.onlineTicketRepository = onlineTicketRepository;
        this.ticketService = ticketService;
    }
    //todo: fix all exceptions

    public OnlineTicket getOnlineTicket(Long id) {
        LOGGER.info("getOnlineTicket() with id: {}", id);
        return onlineTicketRepository.findById(id).orElse(null);
    }

    public OnlineTicketDTO getOnlineTicketDTO(Long id) {
        LOGGER.info("getOnlineTicketDTO() with id: {}", id);
        final OnlineTicket onlineTicket = getOnlineTicket(id);
        if (onlineTicket == null) {
            //throw new NotFoundException();
        }
        return toOnlineTicketDTO(onlineTicket);
    }

    public List<OnlineTicketDTO> getAllOnlineTicketDTOList() {
        LOGGER.info("getAllOnlineTickets()");
        final List<OnlineTicket> onlineTicketList = onlineTicketRepository.findAll();
        final List<OnlineTicketDTO> onlineTicketDTOList = new ArrayList<>();

        for (OnlineTicket onlineTicket : onlineTicketList) {
            final OnlineTicketDTO onlineTicketDTO = toOnlineTicketDTO(onlineTicket);
            onlineTicketDTOList.add(onlineTicketDTO);
        }
        return onlineTicketDTOList;
    }

    public OnlineTicketDTO insertOnlineTicketDTO(OnlineTicketDTO onlineTicketDTO) {
        if (onlineTicketDTO.getId() != null) {
            LOGGER.error("insertCustomerDTO(): there is no body 'id': {}", onlineTicketDTO.getId());
            //throw new BadRequestException();
        }
        OnlineTicket onlineTicket = new OnlineTicket();
        toOnlineTicket(onlineTicketDTO, onlineTicket);

        // Fetch the corresponding Ticket entity based on the ticketDTO's ID
        Ticket ticket = ticketService.getTicket(onlineTicketDTO.getTicketDTO().getId());
        onlineTicket.setTicket(ticket); // Set the ticket to the OnlineTicket entity

        onlineTicket = onlineTicketRepository.save(onlineTicket);
        LOGGER.info("insertOnlineTicketDTO: {}", onlineTicketDTO);
        return toOnlineTicketDTO(onlineTicket);

    }

    public OnlineTicketDTO updateOnlineTicketDTO(OnlineTicketDTO onlineTicketDTO) throws Exception {
        OnlineTicket onlineTicket = onlineTicketRepository.findById(onlineTicketDTO.getId()).orElseThrow(Exception::new);

        toOnlineTicket(onlineTicketDTO , onlineTicket);
        onlineTicket = onlineTicketRepository.save(onlineTicket);

        LOGGER.info("updateCountryDTO() update to: {}", onlineTicketDTO);
        return toOnlineTicketDTO(onlineTicket);
    }

    public void deleteOnlineTicket(Long id) throws Exception {
        if (!onlineTicketRepository.existsById(id)) {
            LOGGER.error("deleteCountry(): country with id {} does not exists.", id);
            throw new Exception("Not Found");
        }
        onlineTicketRepository.deleteById(id);
        LOGGER.info("deleteCountry() with id: {}", id);
    }

    private OnlineTicketDTO toOnlineTicketDTO(OnlineTicket onlineTicket) {
        final OnlineTicketDTO onlineTicketDTO = new OnlineTicketDTO();
        final TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setId(onlineTicket.getTicket().getId());
        onlineTicketDTO.setTicketDTO(ticketDTO);

        onlineTicketDTO.setId(onlineTicket.getId());
        onlineTicketDTO.setEmail(onlineTicket.getEmail());
        onlineTicketDTO.setFirstName(onlineTicket.getFirstName());
        onlineTicketDTO.setLastName(onlineTicket.getLastName());
        return onlineTicketDTO;
    }

    private void toOnlineTicket(OnlineTicketDTO onlineTicketDTO, OnlineTicket onlineTicket) {
        onlineTicket.setEmail(onlineTicketDTO.getEmail());
        onlineTicket.setFirstName(onlineTicketDTO.getFirstName());
        onlineTicket.setLastName(onlineTicketDTO.getLastName());
    }

}
