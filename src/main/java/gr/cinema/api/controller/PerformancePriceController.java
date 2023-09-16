package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.PerformancePriceDTO;
import gr.cinema.api.repository.PerformancePriceRepository;
import gr.cinema.api.service.PerformancePriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PerformancePriceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformancePriceController.class);
    private final PerformancePriceService performancePriceService;

    @Autowired
    public PerformancePriceController(PerformancePriceService performancePriceService){
        this.performancePriceService=performancePriceService;
    }

    @GetMapping(WebConstants.PERF_PRICE_CONTROLLER_GET_ALL_BY_PERFORMANCE_ID) //http://localhost:8080/api/performancePrice/getAllByPerformanceId/1 OK
    public List<PerformancePriceDTO> getAllPerformancePricesByPerformanceId(@PathVariable(WebConstants.ID)Long id){
        return performancePriceService.getAllPerformancePricesByPerformanceIdDTOList(id);
    }
    @GetMapping(WebConstants.PERF_PRICE_CONTROLLER_GET_ALL) //http://localhost:8080/api/performancePrice/getAll OK
    public List<PerformancePriceDTO> getAllPerformancePrices(){
        return performancePriceService.getAllPerformancePriceDTOList();
    }
    @GetMapping(WebConstants.PERF_PRICE_CONTROLLER_GET_PERF_PRICE_BY_ID) //http://localhost:8080/api/performancePrice/getPerfromancePrice/1
    public PerformancePriceDTO getPerformancePrice(@PathVariable(WebConstants.ID)Long id){
        return performancePriceService.getPerformancePriceDTO(id);
    }

    @PostMapping(WebConstants.PERF_PRICE_CONTROLLER_INSERT) //http://localhost:8080/api/performancePrice/insert OK
    public PerformancePriceDTO insertPerformancePrice(@RequestBody PerformancePriceDTO performancePriceDTO){
        return performancePriceService.insertPerformancePriceDTO(performancePriceDTO);
    }

    @PutMapping(WebConstants.PERF_PRICE_CONTROLLER_UPDATE) //http://localhost:8080/api/performancePrice/update/1 OK
    public PerformancePriceDTO updatePerformancePrice(@PathVariable(WebConstants.ID)Long id,@RequestBody PerformancePriceDTO performancePriceDTO)throws Exception{
        if (!id.equals(performancePriceDTO.getId())){
            LOGGER.error("updatePerformancePrice(): path variable 'id': {} and body 'id': {} does not match", id, performancePriceDTO.getId());
            throw new Exception("BadRequest");
        }
        performancePriceDTO.setId(id);
        return performancePriceService.updatePerformancePriceDTO(performancePriceDTO);
    }

    @DeleteMapping(WebConstants.PERF_PRICE_CONTROLLER_DELETE) //http://localhost:8080/api/performancePrice/delete/2 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerformancePrice(@PathVariable(WebConstants.ID)Long id)throws Exception{
        performancePriceService.deletePerformancePrice(id);
    }
}
