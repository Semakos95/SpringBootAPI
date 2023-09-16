package gr.cinema.api.service;

import gr.cinema.api.dto.ImageDTO;
import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.entity.Image;
import gr.cinema.api.entity.Performance;
import gr.cinema.api.repository.ImageRepository;
import gr.cinema.api.repository.PerformanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final PerformanceRepository performanceRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, PerformanceRepository performanceRepository) {
        this.imageRepository = imageRepository;
        this.performanceRepository = performanceRepository;
    }

    public void insertImage(MultipartFile multipartFile, Long perfromanceId) {
        if (multipartFile != null && perfromanceId != null) {
            try {
                Performance performance = this.performanceRepository.findById(perfromanceId).orElse(null);
                if (performance != null && performance.getId() != null) {
                    Image image = new Image();

                    image.setFileName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
                    image.setFileType(multipartFile.getContentType());
                    image.setPerformance(performance);
                    image.setBytes(multipartFile.getBytes());

                    imageRepository.save(image);
                }
            } catch (Exception ex) {
                System.out.println("ERROR UPLOAD");
            }
        }
    }

    public void updateImage(MultipartFile multipartFile, Long perfromanceId) {
        if (multipartFile != null && perfromanceId != null) {
            try {
                Performance performance = this.performanceRepository.findById(perfromanceId).orElse(null);
                Image oldImage = this.imageRepository.findByPerformanceId(perfromanceId);
                if (performance != null && performance.getId() != null) {
                    Image image = new Image();

                    image.setId(oldImage.getId());
                    image.setFileName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
                    image.setFileType(multipartFile.getContentType());
                    image.setPerformance(performance);
                    image.setBytes(multipartFile.getBytes());

                    imageRepository.save(image);
                }
            } catch (Exception ex) {
                System.out.println("ERROR UPDATE");
            }
        }
    }

    public ImageDTO getImage(Long id) {
        ImageDTO imageDTO = new ImageDTO();

        if(id != null){
            Image image = imageRepository.findByPerformanceId(id);
            if(image != null){
                imageDTO.setFileName(image.getFileName());
                imageDTO.setFileType(image.getFileType());
                imageDTO.setBytes(image.getBytes());
            }
        }
        return imageDTO;
    }

}
