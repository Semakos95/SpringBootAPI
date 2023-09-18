package gr.cinema.api.service;

import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.dto.PerformanceStagingDTO;
import gr.cinema.api.dto.RoomDTO;
import gr.cinema.api.entity.Performance;
import gr.cinema.api.entity.PerformanceStaging;
import gr.cinema.api.entity.Room;
import gr.cinema.api.exception.BadRequestException;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.PerformanceStagingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceStagingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceStagingService.class);
    private final PerformanceStagingRepository performanceStagingRepository;
    private final PerformanceService performanceService;
    private final RoomService roomService;

    @Autowired
    public PerformanceStagingService(PerformanceStagingRepository performanceStagingRepository, PerformanceService performanceService, RoomService roomService) {
        this.performanceStagingRepository = performanceStagingRepository;
        this.performanceService = performanceService;
        this.roomService = roomService;
    }

    public List<PerformanceStagingDTO> getAllPerformanceStagingsToStageDTOList(){
        LOGGER.info("getAllPerformanceStagingsToStageDTOList()");
        final List<PerformanceStaging> performanceStagingList = performanceStagingRepository.findByPerformanceToStage(true);
        final List<PerformanceStagingDTO> performanceStagingDTOList = new ArrayList<>();

        for (PerformanceStaging performanceStaging : performanceStagingList) {
            final PerformanceStagingDTO performanceStagingDTO = toPerformanceStagingDTO(performanceStaging);
            performanceStagingDTOList.add(performanceStagingDTO);
        }
        return performanceStagingDTOList;
    }
    public List<Date> getPerformanceStagingByFirstAndLastDate(Long id){
        LOGGER.info("getPerformanceStagingByFirstAndLastDate()");
        List<Date> firstAndLastDate = new ArrayList<Date>();

        if (id != null){
            List<PerformanceStaging> performanceStagingList = performanceStagingRepository.findByPerformanceIdOrderByDate(id);

            if (performanceStagingList != null) {
                if (performanceStagingList.size() > 1) {
                    firstAndLastDate.add(performanceStagingList.get(0).getDate());
                    firstAndLastDate.add(performanceStagingList.get(performanceStagingList.size() - 1).getDate());
                } else if (performanceStagingList.size() == 1) {
                    firstAndLastDate.add(performanceStagingList.get(0).getDate());
                    firstAndLastDate.add(performanceStagingList.get(0).getDate());
                }
            }
        }
        return firstAndLastDate;
    }

    public List<PerformanceStagingDTO> getAllPerformanceStagingByPerformanceIdDTOList(Long id){
        LOGGER.info("getAllPerformanceStagingByPerformanceIdDTOList()");
        final List<PerformanceStaging> performanceStagingList = performanceStagingRepository.findByPerformanceIdOrderByDate(id);
        final List<PerformanceStagingDTO> performanceStagingDTOList = new ArrayList<>();

        for (PerformanceStaging performanceStaging : performanceStagingList) {
            final PerformanceStagingDTO performanceStagingDTO = toPerformanceStagingDTO(performanceStaging);
            performanceStagingDTOList.add(performanceStagingDTO);
        }
        return performanceStagingDTOList;

    }
    public List<PerformanceStagingDTO> getAllPerformanceStagingDTOList(){
        LOGGER.info("getAllPerformanceStagingDTOList()");
        final List<PerformanceStaging> performanceStagingList = performanceStagingRepository.findAll();
        final List<PerformanceStagingDTO> performanceStagingDTOList = new ArrayList<>();

        for (PerformanceStaging performanceStaging : performanceStagingList) {
            final PerformanceStagingDTO performanceStagingDTO = toPerformanceStagingDTO(performanceStaging);
            performanceStagingDTOList.add(performanceStagingDTO);
        }
        return performanceStagingDTOList;
    }

    public List<PerformanceStagingDTO> getPerformanceStagingByDateDTOList(String date) {
        LOGGER.info("getPerformanceStagingByDate()");

        final List<PerformanceStaging> performanceStagingList = performanceStagingRepository.findByDateContaining(date);
        final List<PerformanceStagingDTO> performanceStagingDTOList = new ArrayList<>();

        for (PerformanceStaging performanceStaging : performanceStagingList) {
            final PerformanceStagingDTO performanceStagingDTO = toPerformanceStagingDTO(performanceStaging);
            performanceStagingDTOList.add(performanceStagingDTO);
        }
        return performanceStagingDTOList;
    }


    public PerformanceStaging getPerformanceStaging(Long id) {
        LOGGER.info("getPerformanceStaging() with id: {}", id);
        return performanceStagingRepository.findById(id).orElse(null);
    }

    public PerformanceStagingDTO getPerformanceStagingDTO(Long id) {
        LOGGER.info("getPerformanceStaging() with id: {}", id);
        final PerformanceStaging performanceStaging = getPerformanceStaging(id);

        if (performanceStaging == null) {
            LOGGER.error("getPerformanceStaging() PerformanceStaging with 'ID': {} Does not exist!", id);
            throw new NotFoundException();
        }
        return toPerformanceStagingDTO(performanceStaging);
    }

    public PerformanceStagingDTO insertPerformanceStagingDTO(PerformanceStagingDTO performanceStagingDTO) {
        if (performanceStagingDTO.getId() != null) {
            LOGGER.error("insertPerformanceStagingDTO(): there is a body 'ID': {}", performanceStagingDTO.getId());
            throw new BadRequestException();
        }

        PerformanceStaging performanceStaging = new PerformanceStaging();
        toPerformanceStaging(performanceStagingDTO, performanceStaging);

        Performance performance = performanceService.getPerformance(performanceStagingDTO.getPerformanceDTO().getId());
        performanceStaging.setPerformance(performance);

        Room room = roomService.getRoom(performanceStagingDTO.getRoomDTO().getId());
        performanceStaging.setRoom(room);

        performanceStaging = performanceStagingRepository.save(performanceStaging);
        LOGGER.info("insertPerformanceStagingDTO: You inserted successfully new PerformanceStaging to: {}", performanceStagingDTO);

        return toPerformanceStagingDTO(performanceStaging);
    }

    public PerformanceStagingDTO updatePerformanceStagingDTO(PerformanceStagingDTO performanceStagingDTO) throws Exception {
        PerformanceStaging performanceStaging = performanceStagingRepository.findById(performanceStagingDTO.getId()).orElseThrow(Exception::new);

        toPerformanceStaging(performanceStagingDTO, performanceStaging);
        performanceStaging = performanceStagingRepository.save(performanceStaging);
        LOGGER.info("updatePerformanceStagingDTO() You updated successfully PerformanceStaging to: {}", performanceStagingDTO);

        return toPerformanceStagingDTO(performanceStaging);
    }

    public void deletePerformanceStaging(Long id) throws Exception {
        if (!performanceStagingRepository.existsById(id)) {
            LOGGER.error("deletePerformanceStaging(): PerformanceStaging with 'ID' {} does not exist.", id);
            throw new Exception("NotFound");
        }
        performanceStagingRepository.deleteById(id);
        LOGGER.info("deletePerformanceStaging(): You deleted successfully PerformanceStaging with 'ID': {}", id);
    }

    private PerformanceStagingDTO toPerformanceStagingDTO(PerformanceStaging performanceStaging) {
        final PerformanceStagingDTO performanceStagingDTO = new PerformanceStagingDTO();
        final PerformanceDTO performanceDTO = new PerformanceDTO();
        final RoomDTO roomDTO = new RoomDTO();

        performanceDTO.setId(performanceStaging.getPerformance().getId());
        performanceStagingDTO.setPerformanceDTO(performanceDTO);

        roomDTO.setId(performanceStaging.getRoom().getId());
        performanceStagingDTO.setRoomDTO(roomDTO);

        performanceStagingDTO.setId(performanceStaging.getId());
        performanceStagingDTO.setDate(performanceStaging.getDate());
        performanceStagingDTO.setStartTime(performanceStaging.getStartTime());

        return performanceStagingDTO;
    }

    private void toPerformanceStaging(PerformanceStagingDTO performanceStagingDTO, PerformanceStaging performanceStaging) {
        performanceStaging.setDate(performanceStagingDTO.getDate());
        performanceStaging.setStartTime(performanceStagingDTO.getStartTime());
    }
}
