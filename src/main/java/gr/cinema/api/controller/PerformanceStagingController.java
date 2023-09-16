package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.PerformanceStagingDTO;
import gr.cinema.api.service.PerformanceStagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
public class PerformanceStagingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceStagingController.class);
    private final PerformanceStagingService performanceStagingService;

    @Autowired
    public PerformanceStagingController(PerformanceStagingService performanceStagingService){
        this.performanceStagingService=performanceStagingService;
    }

    @GetMapping(WebConstants.PERF_STAGING_CONTROLLER_GET_ALL_TO_STAGE)//http://localhost:8080/api/performanceStaging/getToStage
    public List<PerformanceStagingDTO> getAllPerformanceStagingsToStage(){
        return performanceStagingService.getAllPerformanceStagingsToStageDTOList();
    }
    @GetMapping(WebConstants.PERF_STAGING_CONTROLLER_GET_FIRST_AND_LAST_DATE) //http://localhost:8080/api/performanceStaging/getFirstAndLastDate/1 OK
    public List<Date> getPerformanceStagingByFirstAndLastDate(@PathVariable(WebConstants.ID) Long id){
        return performanceStagingService.getPerformanceStagingByFirstAndLastDate(id);
    }

    @GetMapping(WebConstants.PERF_STAGING_CONTROLLER_GET_ALL_BY_PERFORMANCE_ID) //http://localhost:8080/api/performanceStaging/getAllByPerformanceId/1 OK
    public List<PerformanceStagingDTO> getAllPerformanceStagingByPerformanceId(@PathVariable(WebConstants.ID) Long id){
        return performanceStagingService.getAllPerformanceStagingByPerformanceIdDTOList(id);
    }

    @GetMapping(WebConstants.PERF_STAGING_CONTROLLER_GET_ALL) //http://localhost:8080/api/performanceStaging/getAll OK
    public List<PerformanceStagingDTO> getAllPerformanceStaging(){
        return performanceStagingService.getAllPerformanceStagingDTOList();
    }

    @GetMapping(WebConstants.PERF_STAGING_CONTROLLER_GET_PERF_STAGING) //http://localhost:8080/api/performanceStaging/getPerformanceStaging/1 OK
    public PerformanceStagingDTO getPerformanceStaging(@PathVariable(WebConstants.ID)Long id){
        return performanceStagingService.getPerformanceStagingDTO(id);
    }

    @PostMapping(WebConstants.PERF_STAGING_CONTROLLER_INSERT) //http://localhost:8080/api/performanceStaging/insert OK (Date has to insert reversed("date": "1995-07-22"))
    public PerformanceStagingDTO insertPerformanceStaging(@RequestBody PerformanceStagingDTO performanceStagingDTO){
        return performanceStagingService.insertPerformanceStagingDTO(performanceStagingDTO);
    }

    @PutMapping(WebConstants.PERF_STAGING_CONTROLLER_UPDATE) //http://localhost:8080/api/performanceStaging/update/1 OK
    public PerformanceStagingDTO updatePerformanceStaging(@PathVariable(WebConstants.ID)Long id, @RequestBody PerformanceStagingDTO performanceStagingDTO)throws Exception{
        if (!id.equals(performanceStagingDTO.getId())){
            LOGGER.error("updatePerformanceStaging(): path variable 'id': {} and body 'id': {} does not match", id, performanceStagingDTO.getId());
            throw new Exception("BadRequest");
        }
        performanceStagingDTO.setId(id);
        return performanceStagingService.updatePerformanceStagingDTO(performanceStagingDTO);
    }

    @DeleteMapping(WebConstants.PERF_STAGING_CONTROLLER_DELETE) //http://localhost:8080/api/performanceStaging/delete/1 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerformanceStaging(@PathVariable(WebConstants.ID)Long id)throws Exception{
        performanceStagingService.deletePerformanceStaging(id);
    }
}
