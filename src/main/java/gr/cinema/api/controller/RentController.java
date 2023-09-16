package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.RentDTO;
import gr.cinema.api.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class RentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);
    private final RentService rentService;
    @Autowired
    public RentController(RentService rentService){
        this.rentService=rentService;
    }


    @GetMapping(WebConstants.RENT_CONTROLLER_GET_ALL_RENTS_BY_DATE)//http://localhost:8080/api/rent/getAllByDate?date=2022-05-05 OK
    public List<RentDTO> getAllRentsByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return rentService.getAllRentsByDateDTOList(date);
    }

    @GetMapping(WebConstants.RENT_CONTROLLER_GET_ALL_BY_USER_ID) //http://localhost:8080/api/rent/getAllByUserId/1 OK
    public List<RentDTO> getAllRentsByUserId(@PathVariable(WebConstants.ID)Long id){
        return rentService.getAllRentsByUserIdDTOList(id);
    }
    @GetMapping(WebConstants.RENT_CONTROLLER_GET_ALL) //http://localhost:8080/api/rent/getAll OK
    public List<RentDTO> getAllRents(){
        return rentService.getAllRentsDTOList();
    }
    @GetMapping(WebConstants.RENT_CONTROLLER_GET_RENT) //http://localhost:8080/api/rent/getRent/1 OK
    public RentDTO getRent(@PathVariable(WebConstants.ID)Long id){
        return rentService.getRentDTO(id);
    }

    @PostMapping(WebConstants.RENT_CONTROLLER_INSERT) //http://localhost:8080/api/rent/insert OK
    public RentDTO insertRent(@RequestBody RentDTO rentDTO)throws Exception{
        return rentService.insertRentDTO(rentDTO);
    }

    @PutMapping(WebConstants.RENT_CONTROLLER_UPDATE) //http://localhost:8080/api/rent/update/2 OK
    public RentDTO updateRent(@PathVariable(WebConstants.ID)Long id,@RequestBody RentDTO rentDTO)throws Exception{
        if (!id.equals(rentDTO.getId())){
            LOGGER.error("updateRent(): path variable 'id': {} and body 'id': {} does not match", id, rentDTO.getId());
            throw new Exception("BadRequest");
        }
        rentDTO.setId(id);
        return rentService.updateRentDTO(rentDTO);
    }

    @DeleteMapping(WebConstants.RENT_CONTROLLER_DELETE) //http://localhost:8080/api/rent/delete/3 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRent(@PathVariable("id")Long id)throws Exception{
        rentService.deleteRent(id);
    }

}
