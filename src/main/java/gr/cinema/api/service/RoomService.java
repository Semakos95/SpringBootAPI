package gr.cinema.api.service;

import gr.cinema.api.dto.RoomDTO;
import gr.cinema.api.entity.Room;
import gr.cinema.api.exception.BadRequestException;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);
    private final RoomRepository roomRepository;


    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTO> getAllRoomDTOList() {
        LOGGER.info("getAllRooms()");
        final List<Room> roomList = roomRepository.findAll();
        final List<RoomDTO> roomDTOList = new ArrayList<>();

        for (Room room : roomList) {
            final RoomDTO roomDTO = toRoomDTO(room);
            roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }

    public Room getRoom(Long id) {
        LOGGER.info("getRoom() with id: {}", id);
        return roomRepository.findById(id).orElse(null);
    }

    public RoomDTO getRoomDTO(Long id) {
        LOGGER.info("getRoom() with id: {}", id);
        final Room room = getRoom(id);

        if (room == null) {
            LOGGER.error("getRoomDTO(): Room with 'ID' {} does not exist.", id);
            throw new NotFoundException();
        }
        return toRoomDTO(room);
    }

    public RoomDTO insertRoomDTO(RoomDTO roomDTO) {
        if (roomDTO.getId() != null) {
            LOGGER.error("insertRoomDTO(): there is a body 'id': {}", roomDTO.getId());
            throw new BadRequestException();
        }
        Room room = new Room();
        toRoom(roomDTO, room);

        room = roomRepository.save(room);
        LOGGER.info("insertRoomDTO: {}", roomDTO);
        return toRoomDTO(room);
    }

    public RoomDTO updateRoomDTO(RoomDTO roomDTO) throws Exception {
        Room room = roomRepository.findById(roomDTO.getId()).orElseThrow(Exception::new);

        toRoom(roomDTO, room);
        room = roomRepository.save(room);
        LOGGER.info("updateRoomDTO() update to: {}", roomDTO);

        return toRoomDTO(room);
    }

    public void deleteRoomDTO(Long id) throws Exception {
        if (!roomRepository.existsById(id)) {
            LOGGER.error("deleteRoom(): room with id {} does not exist.", id);
            throw new Exception("NotFound");
        }
        roomRepository.deleteById(id);
        LOGGER.info("deleteRoom() with id: {}", id);
    }

    private RoomDTO toRoomDTO(Room room) {
        final RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setCostPerDay(room.getCostPerDay());
        roomDTO.setSeats(room.getSeats());
        return roomDTO;
    }

   private void toRoom(RoomDTO roomDTO, Room room){
        room.setCostPerDay(roomDTO.getCostPerDay());
        room.setSeats(roomDTO.getSeats());
    }



}
