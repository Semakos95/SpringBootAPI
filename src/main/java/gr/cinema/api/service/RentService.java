package gr.cinema.api.service;

import gr.cinema.api.dto.UserDTO;
import gr.cinema.api.dto.RentDTO;
import gr.cinema.api.dto.RoomDTO;
import gr.cinema.api.entity.User;
import gr.cinema.api.entity.Rent;
import gr.cinema.api.entity.Room;
import gr.cinema.api.exception.BadRequestException;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.RentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentService.class);
    private final RentRepository rentRepository;
    private final UserService userService;
    private final RoomService roomService;

    @Autowired
    public RentService(RentRepository rentRepository, UserService userService, RoomService roomService) {
        this.rentRepository = rentRepository;
        this.userService = userService;
        this.roomService = roomService;
    }

    public List<RentDTO> getAllRentsByDateDTOList(LocalDate date){
        LOGGER.info("getAllRentsByDateDTOList()");
        final List<Rent> rentList = rentRepository.findByDate(date);
        final List<RentDTO> rentDTOList = new ArrayList<>();

        for (Rent rent : rentList) {
            final RentDTO rentDTO = toRentDTO(rent);
            rentDTOList.add(rentDTO);
        }
        return rentDTOList;
    }

    public List<RentDTO> getAllRentsDTOList() {
        LOGGER.info("getAllRentsDTOList()");
        final List<Rent> rentList = rentRepository.findAll();
        final List<RentDTO> rentDTOList = new ArrayList<>();

        for (Rent rent : rentList) {
            final RentDTO rentDTO = toRentDTO(rent);
            rentDTOList.add(rentDTO);
        }
        return rentDTOList;
    }

    public List<RentDTO> getAllRentsByUserIdDTOList(Long id){
        LOGGER.info("getAllRentsByCustomerIdDTOList()");
        final List<Rent> rentList = rentRepository.findByUserIdOrderByDateDesc(id);
        final List<RentDTO> rentDTOList = new ArrayList<>();

        for (Rent rent : rentList) {
            final RentDTO rentDTO = toRentDTO(rent);
            rentDTOList.add(rentDTO);
        }
        return rentDTOList;
    }

    public Rent getRent(Long id) {
        LOGGER.info("getRent() with 'ID': {}", id);
        return rentRepository.findById(id).orElse(null);
    }

    public RentDTO getRentDTO(Long id) {
        LOGGER.info("getRentDTO() with 'ID': {}", id);
        final Rent rent = getRent(id);

        if (rent == null) {
            LOGGER.error("getRentDTO() Rent with 'ID': {} Does not exist!", id);
            throw new NotFoundException();
        }
        return toRentDTO(rent);
    }

    public RentDTO insertRentDTO(RentDTO rentDTO) {
        if (rentDTO.getId() != null) {
            LOGGER.error("insertRentDTO(): there is a body 'ID': {}", rentDTO.getId());
            throw new BadRequestException();
        }
        // Check if there is already a rent record with the same date and period
        List<Rent> existingRent = rentRepository.findByDateAndPeriod(rentDTO.getDate(),rentDTO.getPeriod());

        if (!existingRent.isEmpty()){
            LOGGER.error("insertRentDTO(): that Date and period is already taken: ({} at {}) Please try again with other Date/Period", rentDTO.getDate(),rentDTO.getPeriod());
           // Handle the error, throw an exception, or return an error response
            throw new BadRequestException();
        }

        Rent rent = new Rent();
        toRent(rentDTO, rent);

        User user = userService.getUser(rentDTO.getUserDTO().getId());
        rent.setCustomer(user);

        Room room = roomService.getRoom(rentDTO.getRoomDTO().getId());
        rent.setRoom(room);

        rent = rentRepository.save(rent);
        LOGGER.info("insertRentDTO: You inserted successfully new Rent to: {}", rentDTO);

        return toRentDTO(rent);
    }

    public RentDTO updateRentDTO(RentDTO rentDTO) throws Exception {
        Rent rent = rentRepository.findById(rentDTO.getId()).orElseThrow(Exception::new);

        toRent(rentDTO, rent);
        rent = rentRepository.save(rent);
        LOGGER.info("updateRentDTO(): You updated successfully Rent to: {}", rentDTO);

        return toRentDTO(rent);
    }

    public void deleteRent(Long id) throws Exception {
        if (!rentRepository.existsById(id)) {
            LOGGER.error("deleteRent(): Rent with 'ID' {} does not exist.", id);
            throw new Exception("NotFound");
        }
        rentRepository.deleteById(id);
        LOGGER.info("deleteRent(): You deleted successfully Rent with 'ID': {}", id);
    }

    private RentDTO toRentDTO(Rent rent) {
        final RentDTO rentDTO = new RentDTO();
        final UserDTO userDTO = new UserDTO();
        final RoomDTO roomDTO = new RoomDTO();

        userDTO.setId(rent.getCustomer().getId());
        rentDTO.setUserDTO(userDTO);

        roomDTO.setId(rent.getRoom().getId());
        rentDTO.setRoomDTO(roomDTO);

        rentDTO.setId(rent.getId());
        rentDTO.setDate(rent.getDate());
        rentDTO.setPeriod(rent.getPeriod());

        return rentDTO;
    }

    private void toRent(RentDTO rentDTO, Rent rent) {
        rent.setDate(rentDTO.getDate());
        rent.setPeriod(rentDTO.getPeriod());
    }
}
