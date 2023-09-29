package gr.cinema.api.service;

import gr.cinema.api.dto.TheaterDTO;
import gr.cinema.api.entity.Theater;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.TheaterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheaterService.class);
    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater getTheater(Long id) {
        LOGGER.info("getTheater() with id: {}", id);
        return theaterRepository.findById(id).orElse(null);
    }

    public TheaterDTO getTheaterDTO(Long id) {
        LOGGER.info("getTheaterDTO() with id: {}", id);
        final Theater theater = getTheater(id);

        if (theater == null) {
            LOGGER.error("getTheaterDTO() with id: {} does not exist" , id);
            throw new NotFoundException();
        }
        return toTheaterDTO(theater);
    }

    public TheaterDTO insertTheaterDTO(TheaterDTO theaterDTO) {
        if (theaterDTO.getId() != null) {
            LOGGER.error("insertTheaterDTO(): there is a body 'id': {}", theaterDTO.getId());
        }
        Theater theater = new Theater();
        toTheater(theaterDTO, theater);

        theater = theaterRepository.save(theater);
        LOGGER.info("insertTheaterDTO: {}", theaterDTO);
        return toTheaterDTO(theater);
    }

    public TheaterDTO updateTheaterDTO(TheaterDTO theaterDTO) throws Exception {
        Theater theater = theaterRepository.findById(theaterDTO.getId()).orElseThrow(Exception::new);

        toTheater(theaterDTO, theater);
        theater = theaterRepository.save(theater);
        LOGGER.info("updateTheaterDTO() update to: {}", theaterDTO);

        return toTheaterDTO(theater);
    }

    public void deleteTheaterDTO(Long id) throws Exception {
        if (!theaterRepository.existsById(id)) {
            LOGGER.error("deleteTheater(): theater with id {} does not exist.", id);
            throw new Exception("NotFound");
        }
        theaterRepository.deleteById(id);
        LOGGER.info("deleteTheater() with id: {}", id);
    }

    private TheaterDTO toTheaterDTO(Theater theater) {
        final TheaterDTO theaterDTO = new TheaterDTO();
        theaterDTO.setId(theater.getId());
        theaterDTO.setAddress(theater.getAddress());
        theaterDTO.setEmail(theater.getEmail());
        theaterDTO.setPhone(theater.getPhone());
        theaterDTO.setPostalCode(theater.getPostalCode());
        return theaterDTO;
    }

    private void toTheater(TheaterDTO theaterDTO, Theater theater) {
        theater.setAddress(theaterDTO.getAddress());
        theater.setEmail(theaterDTO.getEmail());
        theater.setPhone(theaterDTO.getPhone());
        theater.setPostalCode(theaterDTO.getPostalCode());
    }
}
