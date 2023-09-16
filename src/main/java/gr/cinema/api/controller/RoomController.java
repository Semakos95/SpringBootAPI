package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.RoomDTO;
import gr.cinema.api.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(WebConstants.ROOM_CONTROLLER_GET_ALL) //http://localhost:8080/api/room/getAll OK
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRoomDTOList();
    }

    @GetMapping(WebConstants.ROOM_CONTROLLER_GET_ROOM) //http://localhost:8080/api/room/getRoom/1 OK
    public RoomDTO getRoom(@PathVariable(WebConstants.ID) Long id) {
        return roomService.getRoomDTO(id);
    }

    @PostMapping(WebConstants.ROOM_CONTROLLER_INSERT_ROOM) //http://localhost:8080/api/room/insert OK
    public RoomDTO insertRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.insertRoomDTO(roomDTO);
    }

    @PutMapping(WebConstants.ROOM_CONTROLLER_UPDATE_ROOM) //http://localhost:8080/api/room/update/1 OK
    public RoomDTO updateRoom(@PathVariable(WebConstants.ID) Long id, @RequestBody RoomDTO roomDTO) throws Exception {
        if (!id.equals(roomDTO.getId())) {
            LOGGER.error("updateRoom(): path variable 'id': {} and body 'id': {} does not match", id, roomDTO.getId());
            throw new Exception("BadRequest");
        }
        roomDTO.setId(id);
        return roomService.updateRoomDTO(roomDTO);
    }

    @DeleteMapping(WebConstants.ROOM_CONTROLLER_DELETE_ROOM) //http://localhost:8080/api/room/delete/5 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable("id") Long id) throws Exception {
        roomService.deleteRoomDTO(id);
    }

}
