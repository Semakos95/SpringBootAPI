package gr.cinema.api.service;

import gr.cinema.api.dto.RoomDTO;
import gr.cinema.api.dto.SectionDTO;
import gr.cinema.api.entity.Room;
import gr.cinema.api.entity.Section;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SectionService.class);
    private final SectionRepository sectionRepository;
    private final RoomService roomService;

    @Autowired
    public SectionService(SectionRepository sectionRepository, RoomService roomService) {
        this.sectionRepository = sectionRepository;
        this.roomService = roomService;
    }

    public List<SectionDTO> getAllSectionDTOList() {
        LOGGER.info("getAllSections()");

        final List<Section> sectionList = sectionRepository.findAll();
        final List<SectionDTO> sectionDTOList = new ArrayList<>();

        for (Section section : sectionList) {
            final SectionDTO sectionDTO = toSectionDTO(section);
            sectionDTOList.add(sectionDTO);
        }
        return sectionDTOList;
    }

    public SectionDTO getSectionDTOByNameAndRoomId(String name, Long id){
        LOGGER.info("getSectionByNameAndRoomId(): ");
        final Section section = sectionRepository.findByNameAndRoomId(name,id);

        if (section == null){
            LOGGER.error("getSectionByNameAndRoomId(): Section by 'Name': {} and 'ID': {} does not exist.", name , id);
            throw new NotFoundException();
        }
        return toSectionDTO(section);
    }

    public Section getSection(Long id) {
        LOGGER.info("getSection() with id : {}", id);
        return sectionRepository.findById(id).orElse(null);
    }

    public SectionDTO getSectionDTO(Long id) {
        LOGGER.info("getSectionDTO() with id : {}", id);
        final Section section = getSection(id);

        if (section == null) {
            LOGGER.error("getSectionDTO(): Section with 'ID' {} does not exist.", id);
            throw new NotFoundException();
        }
        return toSectionDTO(section);
    }

    public SectionDTO insertSectionDTO(SectionDTO sectionDTO) {
        if (sectionDTO.getId() != null) {
            LOGGER.error("insertSectionDTO(): there is a body 'id': {}", sectionDTO.getId());
        }
        Section section = new Section();
        toSection(sectionDTO, section);

        Room room = roomService.getRoom(sectionDTO.getRoomDTO().getId());
        section.setRoom(room);

        section = sectionRepository.save(section);
        LOGGER.info("insertSectionDTO: {}", sectionDTO);

        return toSectionDTO(section);
    }

    public SectionDTO updateSectionDTO(SectionDTO sectionDTO) throws Exception {
        Section section = sectionRepository.findById(sectionDTO.getId()).orElseThrow(Exception::new);

        toSection(sectionDTO, section);
        section = sectionRepository.save(section);

        LOGGER.info("updateSectionDTO() update to: {}", sectionDTO);
        return toSectionDTO(section);
    }

    public void deleteSectionDTO(Long id) throws Exception {
        if (!sectionRepository.existsById(id)) {
            LOGGER.error("deleteSection(): section with id {} does not exist.", id);
            throw new Exception("NotFound");
        }
        sectionRepository.deleteById(id);
        LOGGER.info("deleteSection() with id: {}", id);
    }

    private SectionDTO toSectionDTO(Section section) {
        final SectionDTO sectionDTO = new SectionDTO();
        final RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(section.getRoom().getId());
        roomDTO.setCostPerDay(section.getRoom().getCostPerDay());
        roomDTO.setSeats(section.getRoom().getSeats());
        sectionDTO.setRoomDTO(roomDTO);

        sectionDTO.setId(section.getId());
        sectionDTO.setName(section.getName());
        sectionDTO.setRowss(section.getRowss());
        sectionDTO.setSeats(section.getSeats());
        return sectionDTO;
    }

    private void toSection(SectionDTO sectionDTO, Section section) {
        section.setName(sectionDTO.getName());
        section.setRowss(sectionDTO.getRowss());
        section.setSeats(sectionDTO.getSeats());
    }
}
