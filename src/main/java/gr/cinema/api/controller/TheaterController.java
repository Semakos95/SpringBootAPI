package gr.cinema.api.controller;

import gr.cinema.api.dto.TheaterDTO;
import gr.cinema.api.service.TheaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheaterController.class);
    private final TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/{id}") //http://localhost:8080/theater/1 OK
    public TheaterDTO getTheater(@PathVariable("id") Long id) {
        return theaterService.getTheaterDTO(id);
    }

    @PostMapping //http://localhost:8080/theater OK
    public TheaterDTO insertTheater(@RequestBody TheaterDTO theaterDTO) {
        return theaterService.insertTheaterDTO(theaterDTO);
    }

    @PutMapping("/{id}") //http://localhost:8080/theater/1 OK
    public TheaterDTO updateTheater(@PathVariable("id") Long id, @RequestBody TheaterDTO theaterDTO) throws Exception {
        if (!id.equals(theaterDTO.getId())) {
            LOGGER.error("updateTheater(): path variable 'id': {} and body 'id': {} does not match", id, theaterDTO.getId());
            throw new Exception("BadRequest");
        }
        theaterDTO.setId(id);
        return theaterService.updateTheaterDTO(theaterDTO);
    }

    @DeleteMapping("/{id}") //http://localhost:8080/theater/1 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTheater(@PathVariable("id") Long id) throws Exception {
        theaterService.deleteTheaterDTO(id);
    }

}
