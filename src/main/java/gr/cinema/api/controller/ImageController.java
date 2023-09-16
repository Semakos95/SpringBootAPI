package gr.cinema.api.controller;
import gr.cinema.api.constants.WebConstants;
import gr.cinema.api.dto.ImageDTO;
import gr.cinema.api.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class ImageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService){
        this.imageService=imageService;
    }

    @GetMapping(WebConstants.IMAGE_CONTROLLER_DOWNLOAD)
    public ResponseEntity<Resource> downloadFile(@PathVariable(WebConstants.ID) Long fileId) {
        // Load file from database
        ImageDTO dbFile = null;
        try {
            dbFile = imageService.getImage(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getBytes()));
    }

    @PostMapping(WebConstants.IMAGE_CONTROLLER_INSERT)
    public ResponseEntity<?> insertImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        try {
            imageService.insertImage(file,id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(WebConstants.IMAGE_CONTROLLER_UPDATE)
    public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file,@PathVariable("id") Long id) {
        try {
            imageService.updateImage(file,id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
