package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.service.PerformanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class PerformanceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceController.class);
    private final PerformanceService performanceService;

    @Autowired
    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }
    @GetMapping(WebConstants.PERFORMANCE_CONTROLLER_GET_ALL_PERFORMANCES_TO_STAGE)//http://localhost:8080/api/performance/getAllToStage
    public List<PerformanceDTO> getAllPerformanceToStage(){
        return performanceService.getAllPerfromanceToStageDTOList();
    }

    @GetMapping(WebConstants.PERFORMANCE_CONTROLLER_GET_ALL_STAGED_BY_DATE) //http://localhost:8080/api/performance/getAllStagedByDate?date=2022-05-01 OK
    public List<PerformanceDTO> getAllPerformanceStagingByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return performanceService.getAllPerformanceByDateDTOList(date);
    }

    @GetMapping(WebConstants.PERFORMANCE_CONTROLLER_GET_ALL_BY_USER_ID) //http://localhost:8080/api/performance/getAllByUserId/1 OK
    public List<PerformanceDTO> getAllPerformanceByUserId(@PathVariable(WebConstants.ID) Long id){
        return performanceService.getAllPerformanceByUserId(id);
    }

    @GetMapping(WebConstants.PERFORMANCE_CONTROLLER_GET_BY_TITLE) //http://localhost:8080/api/performance/byTitle?keyword=The Time Traveler's Dream OK
    public List<PerformanceDTO> searchPerformancesByTitle(@RequestParam String keyword) {
        return performanceService.searchPerformancesByTitleDTOList(keyword);
    }

    @GetMapping(WebConstants.PERFORMANCE_CONTROLLER_GET_ALL) //http://localhost:8080/api/performance/getAll OK
    public List<PerformanceDTO> getAllPerformances() {
        return performanceService.getAllPerformanceDTOList();
    }

    @GetMapping(WebConstants.PERFORMANCE_CONTROLLER_GET_PERFORMANCE) //http://localhost:8080/api/performance/getPerformance/1 OK
    public PerformanceDTO getPerformance(@PathVariable(WebConstants.ID) Long id) {
        return performanceService.getPerformanceDTO(id);
    }

    @PostMapping(WebConstants.PERFORMANCE_CONTROLLER_INSERT) //http://localhost:8080/api/performance/insert OK (insert with "userDTO","ticketDTO","performanceStagingDTO":{"id":1}, on json body ) OK
    public PerformanceDTO insertPerformance(@RequestBody PerformanceDTO performanceDTO) {
        return performanceService.insertPerformanceDTO(performanceDTO);
    }

    @PutMapping(WebConstants.PERFORMANCE_CONTROLLER_UPDATE) //http://localhost:8080/api/performance/update/1 OK (insert and id on json body("id": 1))
    public PerformanceDTO updatePerformance(@PathVariable(WebConstants.ID) Long id, @RequestBody PerformanceDTO performanceDTO) throws Exception {
        if (!id.equals(performanceDTO.getId())) {
            LOGGER.error("updatePerformance(): path variable 'id': {} and body 'id': {} does not match", id, performanceDTO.getId());
            throw new Exception("BadRequest");
        }
        performanceDTO.setId(id);
        return performanceService.updatePerformanceDTO(performanceDTO);
    }

    @DeleteMapping(WebConstants.PERFORMANCE_CONTROLLER_DELETE) // http://localhost:8080/api/performance/delete/2 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerformance(@PathVariable(WebConstants.ID) Long id) throws Exception {
        performanceService.deletePerformance(id);
    }

}
