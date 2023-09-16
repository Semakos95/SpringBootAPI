package gr.cinema.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.entity.User;
import gr.cinema.api.entity.Performance;
import gr.cinema.api.repository.PerformanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceService.class);
    private final PerformanceRepository performanceRepository;
    private final UserService userService;

    @Autowired
    public PerformanceService(PerformanceRepository performanceRepository, UserService userService) {
        this.performanceRepository = performanceRepository;
        this.userService = userService;
    }

    public List<PerformanceDTO> getAllPerfromanceToStageDTOList(){
        LOGGER.info("getAllPerformanceToStageDTOList()");
        final List<Performance> performanceList = performanceRepository.findByToStage(true);
        final List<PerformanceDTO> performanceDTOList = new ArrayList<>();

        for (Performance performance : performanceList) {
            final PerformanceDTO performanceDTO = toPerformanceDTO(performance);
            performanceDTOList.add(performanceDTO);
        }
        return performanceDTOList;
    }
    public List<PerformanceDTO> getAllPerformanceByDateDTOList(Date date){
        LOGGER.info("getAllPerformanceByDateDTOList()");
        final List<Performance> performanceList = performanceRepository.findAllStagingPerformanceByDate(date);
        final List<PerformanceDTO> performanceDTOList = new ArrayList<>();

        for (Performance performance : performanceList) {
            final PerformanceDTO performanceDTO = toPerformanceDTO(performance);
            performanceDTOList.add(performanceDTO);
        }
        return performanceDTOList;
    }

    public List<PerformanceDTO> getAllPerformanceByUserId(Long id){
        LOGGER.info("getAllPerformanceByUserId()");
        final List<Performance> performanceList = performanceRepository.findByUserId(id);
        final List<PerformanceDTO> performanceDTOList = new ArrayList<>();

        for (Performance performance : performanceList) {
            final PerformanceDTO performanceDTO = toPerformanceDTO(performance);
            performanceDTOList.add(performanceDTO);
        }
        return performanceDTOList;
    }

    public List<PerformanceDTO> searchPerformancesByTitleDTOList(String keyword) {
        LOGGER.info("getPerformanceByTitleContaining()");
        final List<Performance> performanceList = performanceRepository.findByTitleContaining(keyword);
        final List<PerformanceDTO> performanceDTOList = new ArrayList<>();

        for (Performance performance : performanceList) {
            final PerformanceDTO performanceDTO = toPerformanceDTO(performance);
            performanceDTOList.add(performanceDTO);
        }
        return performanceDTOList;
    }

    public List<PerformanceDTO> getAllPerformanceDTOList() {
        LOGGER.info("getAllPerformances()");

        final List<Performance> performanceList = performanceRepository.findAll();
        final List<PerformanceDTO> performanceDTOList = new ArrayList<>();

        for (Performance performance : performanceList) {
            final PerformanceDTO performanceDTO = toPerformanceDTO(performance);
            performanceDTOList.add(performanceDTO);
        }
        return performanceDTOList;
    }

    public Performance getPerformance(Long id) {
        LOGGER.info("getPerformance() with id: {}", id);
        return performanceRepository.findById(id).orElse(null);
    }

    public PerformanceDTO getPerformanceDTO(Long id) {
        LOGGER.info("getPerformanceDTO() with id: {}", id);
        final Performance performance = getPerformance(id);

        if (performance == null) {
            //throw new NotFoundException();
        }
        return toPerformanceDTO(performance);
    }

    public PerformanceDTO insertPerformanceDTO(PerformanceDTO performanceDTO) {
        if (performanceDTO.getId() != null) {
            LOGGER.error("insertPerformanceDTO(): there is a body 'id': {}", performanceDTO.getId());
            //throw new BadRequestException();
        }
        Performance performance = new Performance();
        toPerformance(performanceDTO, performance);

        User user = userService.getUser(performanceDTO.getUserDTO().getId());
        performance.setUser(user);

        performance = performanceRepository.save(performance);
        LOGGER.info("insertPerformanceDTO(): {}", performanceDTO);

        return toPerformanceDTO(performance);
    }

    public PerformanceDTO updatePerformanceDTO(PerformanceDTO performanceDTO) throws Exception {
        Performance performance = performanceRepository.findById(performanceDTO.getId()).orElseThrow(Exception::new);

        toPerformance(performanceDTO, performance);
        performance = performanceRepository.save(performance);
        LOGGER.info("updatePerformanceDTO() update to: {} ", performanceDTO);

        return toPerformanceDTO(performance);
    }

    public void deletePerformance(Long id) throws Exception {
        if (!performanceRepository.existsById(id)) {
            LOGGER.error("deletePerformance(): performance with id {} does not exist.", id);
            throw new Exception("NotFound");
        }
        performanceRepository.deleteById(id);
        LOGGER.info("deletePerformance() with id: {}", id);
    }

    public PerformanceDTO toPerformanceDTO(Performance performance) {
        final PerformanceDTO performanceDTO = new PerformanceDTO();
        final UserDTO userDTO = new UserDTO();

        userDTO.setId(performance.getUser().getId());
        performanceDTO.setUserDTO(userDTO);

        performanceDTO.setId(performance.getId());
        performanceDTO.setActors(performance.getActors());
        performanceDTO.setCategory(performance.getCategory());
        performanceDTO.setCostumes(performance.getCostumes());
        performanceDTO.setDirection(performance.getDirection());
        performanceDTO.setDuration(performanceDTO.getDuration());
        performanceDTO.setTitle(performance.getTitle());
        performanceDTO.setProduction(performance.getProduction());
        performanceDTO.setStars(performance.getStars());
        performanceDTO.setSummary(performance.getSummary());
        performanceDTO.setToStage(performanceDTO.getToStage());
        performanceDTO.setTrailer(performance.getTrailer());
        return performanceDTO;
    }

    private void toPerformance(PerformanceDTO performanceDTO, Performance performance) {
        performance.setActors(performanceDTO.getActors());
        performance.setCategory(performanceDTO.getCategory());
        performance.setCostumes(performanceDTO.getCostumes());
        performance.setDirection(performanceDTO.getDirection());
        performance.setDuration(performanceDTO.getDuration());
        performance.setTitle(performanceDTO.getTitle());
        performance.setProduction(performanceDTO.getProduction());
        performance.setStars(performanceDTO.getStars());
        performance.setSummary(performanceDTO.getSummary());
        performance.setToStage(performanceDTO.getToStage());
        performance.setTrailer(performanceDTO.getTrailer());
    }

}
