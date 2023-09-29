package gr.cinema.api.service;

import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.dto.RoomDTO;
import gr.cinema.api.dto.SectionDTO;
import gr.cinema.api.dto.TicketDTO;
import gr.cinema.api.entity.Performance;
import gr.cinema.api.entity.Room;
import gr.cinema.api.entity.Section;
import gr.cinema.api.entity.Ticket;
import gr.cinema.api.exception.BadRequestException;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);
    private final TicketRepository ticketRepository;
    private final PerformanceService performanceService;
    private final RoomService roomService;
    private final SectionService sectionService;



    @Autowired
    public TicketService(TicketRepository ticketRepository, PerformanceService performanceService, RoomService roomService, SectionService sectionService) {
        this.ticketRepository = ticketRepository;
        this.performanceService = performanceService;
        this.roomService = roomService;
        this.sectionService = sectionService;
    }

    public List<TicketDTO> getSpecificTicketsDTOList(Long performanceId, Long roomId, Long sectionId, LocalDate date){
        LOGGER.info("getSpecificTicketsDTOList()");
        final List<Ticket> ticketList = ticketRepository.findByPerformanceIdAndRoomIdAndSectionIdAndDateOrderByDate(performanceId, roomId, sectionId, date);
        final List<TicketDTO> ticketDTOList = new ArrayList<>();

        for (Ticket ticket : ticketList){
            final TicketDTO ticketDTO = toTicketDTO(ticket);
            ticketDTOList.add(ticketDTO);
        }
        return ticketDTOList;
    }

    public List<TicketDTO> getAllTicketDTOList() {
        LOGGER.info("getAllTickets()");

        final List<Ticket> ticketList = ticketRepository.findAll();
        final List<TicketDTO> ticketDTOList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            final TicketDTO ticketDTO = toTicketDTO(ticket);
            ticketDTOList.add(ticketDTO);
        }
        return ticketDTOList;
    }

    public Ticket getTicket(Long id) {
        LOGGER.info("getTicket() with id: {}", id);
        return ticketRepository.findById(id).orElse(null);
    }

    public TicketDTO getTicketDTO(Long id) {
        LOGGER.info("getTicketDTO() with id: {}", id);
        final Ticket ticket = getTicket(id);

        if (ticket == null) {
            LOGGER.error("getTicketDTO() with id: {} does not exist" , id);
            throw new NotFoundException();
        }
        return toTicketDTO(ticket);
    }

    public TicketDTO insertTicketDTO(TicketDTO ticketDTO) {
        if (ticketDTO.getId() != null) {
            LOGGER.error("insertTicketDTO(): there is a body 'id': {}", ticketDTO.getId());
            throw new BadRequestException();
        }
        Ticket ticket = new Ticket();
        toTicket(ticketDTO, ticket);

        Performance performance = performanceService.getPerformance(ticketDTO.getPerformanceDTO().getId());
        ticket.setPerformance(performance);

        Room room = roomService.getRoom(ticketDTO.getRoomDTO().getId());
        ticket.setRoom(room);

        Section section = sectionService.getSection(ticketDTO.getSectionDTO().getId());
        ticket.setSection(section);

        ticket = ticketRepository.save(ticket);
        LOGGER.info("insertTicketDTO: {}", ticketDTO);

        return toTicketDTO(ticket);
    }

    public TicketDTO updateTicketDTO(TicketDTO ticketDTO) throws Exception {
        Ticket ticket = ticketRepository.findById(ticketDTO.getId()).orElseThrow(Exception::new);

        toTicket(ticketDTO, ticket);
        ticket = ticketRepository.save(ticket);
        LOGGER.info("updateTicketDTO() update to: {}", ticketDTO);

        return toTicketDTO(ticket);
    }

    public void deleteTicketDTO(Long id) throws Exception {
        if (!ticketRepository.existsById(id)) {
            LOGGER.error("deleteTicket(): ticket with id {} does not exist.", id);
            throw new Exception("NotFound");
        }
        ticketRepository.deleteById(id);
        LOGGER.info("deleteTicket() with id: {}", id);
    }

    private TicketDTO toTicketDTO(Ticket ticket) {
        final TicketDTO ticketDTO = new TicketDTO();
        final PerformanceDTO performanceDTO = new PerformanceDTO();
        final RoomDTO roomDTO = new RoomDTO();
        final SectionDTO sectionDTO = new SectionDTO();

        performanceDTO.setId(ticket.getPerformance().getId());
        ticketDTO.setPerformanceDTO(performanceDTO);

        roomDTO.setId(ticket.getRoom().getId());
        ticketDTO.setRoomDTO(roomDTO);

        sectionDTO.setId(ticket.getSection().getId());
        ticketDTO.setSectionDTO(sectionDTO);

        ticketDTO.setId(ticket.getId());
        ticketDTO.setDate(ticket.getDate());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.setRowss(ticket.getRowss());
        ticketDTO.setSeat(ticket.getSeat());
        ticketDTO.setTime(ticket.getTime());
        return ticketDTO;
    }

    private void toTicket(TicketDTO ticketDTO, Ticket ticket) {
        ticket.setDate(ticketDTO.getDate());
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setRowss(ticketDTO.getRowss());
        ticket.setSeat(ticketDTO.getSeat());
        ticket.setTime(ticketDTO.getTime());
    }
}
