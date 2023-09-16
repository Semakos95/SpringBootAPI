package gr.cinema.api.controller;

import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.SectionDTO;
import gr.cinema.api.service.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SectionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SectionController.class);
    private SectionService sectionService;
    @Autowired
    public SectionController(SectionService sectionService){
        this.sectionService=sectionService;
    }

    @GetMapping(WebConstants.SECTION_CONTROLLER_GET_BY_NAME_AND_ROOM_ID) //http://localhost:8080/api/section/getSectionByNameAndRoomId?name=george&id=1 OK
    public SectionDTO getSectionByNameAndRoomId(@RequestParam(WebConstants.NAME)String name, @RequestParam(WebConstants.ID)Long id){
        return sectionService.getSectionDTOByNameAndRoomId(name,id);
    }
    @GetMapping(WebConstants.SECTION_CONTROLLER_GET_ALL) //http://localhost:8080/api/section/getAll OK
    public List<SectionDTO> getAllSections(){
        return sectionService.getAllSectionDTOList();
    }

    @GetMapping(WebConstants.SECTION_CONTROLLER_GET_SECTION) //http://localhost:8080/api/section/getSection/1 OK
    public SectionDTO getSection(@PathVariable(WebConstants.ID) Long id){
        return sectionService.getSectionDTO(id);
    }

    @PostMapping(WebConstants.SECTION_CONTROLLER_INSERT_SECTION) //http://localhost:8080/api/section/insert OK (enter in json "roomDTO": { "id": 1 } to connect them correctly)
    public SectionDTO insertSection(@RequestBody SectionDTO sectionDTO){
        return sectionService.insertSectionDTO(sectionDTO);
    }

    @PutMapping(WebConstants.SECTION_CONTROLLER_UPDATE_SECTION) //http://localhost:8080/api/section/update/1 OK
    public SectionDTO updateSection(@PathVariable(WebConstants.ID) Long id, @RequestBody SectionDTO sectionDTO)throws Exception{
        if (!id.equals(sectionDTO.getId())){
            LOGGER.error("updateSection(): path variable 'id': {} and body 'id': {} does not match", id, sectionDTO.getId());
            throw new Exception("BadRequest");
        }
        sectionDTO.setId(id);
        return sectionService.updateSectionDTO(sectionDTO);
    }

    @DeleteMapping(WebConstants.SECTION_CONTROLLER_DELETE_SECTION) //http://localhost:8080/api/section/delete/5 OK
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSection(@PathVariable(WebConstants.ID) Long id)throws Exception{
        sectionService.deleteSectionDTO(id);
    }

}
