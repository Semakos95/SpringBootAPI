package gr.cinema.api.service;

import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.dto.PerformancePriceDTO;
import gr.cinema.api.dto.SectionDTO;
import gr.cinema.api.entity.Performance;
import gr.cinema.api.entity.PerformancePrice;
import gr.cinema.api.entity.Section;
import gr.cinema.api.exception.BadRequestException;
import gr.cinema.api.exception.NotFoundException;
import gr.cinema.api.repository.PerformancePriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformancePriceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformancePriceService.class);
    private final PerformancePriceRepository performancePriceRepository;
    private final PerformanceService performanceService;
    private final SectionService sectionService;

    @Autowired
    public PerformancePriceService(PerformancePriceRepository performancePriceRepository, PerformanceService performanceService, SectionService sectionService) {
        this.performancePriceRepository = performancePriceRepository;
        this.performanceService = performanceService;
        this.sectionService = sectionService;
    }

    public PerformancePrice getPerformancePrice(Long id) {
        LOGGER.info("getPerformancePrice() with 'ID': {}", id);
        return performancePriceRepository.findById(id).orElse(null);
    }

    public PerformancePriceDTO getPerformancePriceDTO(Long id) {
        LOGGER.info("getPerformancePriceDTO() with 'ID': {}", id);
        final PerformancePrice performancePrice = getPerformancePrice(id);

        if (performancePrice == null) {
            LOGGER.error("getPerformancePriceDTO() PerformancePrice with 'ID': {} Does not exist!", id);
            throw new NotFoundException();
        }
        return toPerformancePriceDTO(performancePrice);
    }

    public List<PerformancePriceDTO> getAllPerformancePricesByPerformanceIdDTOList(Long id){
        LOGGER.info("getAllPerformancePricesByPerformanceIdDTOList()");
        final List<PerformancePrice> performancePriceList = performancePriceRepository.findByPerformanceId(id);
        final List<PerformancePriceDTO> performancePriceDTOList = new ArrayList<>();

        for (PerformancePrice performancePrice : performancePriceList){
            final PerformancePriceDTO performancePriceDTO = toPerformancePriceDTO(performancePrice);
            performancePriceDTOList.add(performancePriceDTO);
        }

        return performancePriceDTOList;
    }

    public List<PerformancePriceDTO> getAllPerformancePriceDTOList(){
        LOGGER.info("getAllPerformancePriceDTOList()");
        final List<PerformancePrice> performancePriceList = performancePriceRepository.findAll();
        final List<PerformancePriceDTO> performancePriceDTOList = new ArrayList<>();

        for (PerformancePrice performancePrice : performancePriceList){
            final PerformancePriceDTO performancePriceDTO = toPerformancePriceDTO(performancePrice);
            performancePriceDTOList.add(performancePriceDTO);
        }
        return performancePriceDTOList;
    }

    public PerformancePriceDTO insertPerformancePriceDTO(PerformancePriceDTO performancePriceDTO) {
        if (performancePriceDTO.getId() != null) {
            LOGGER.error("insertPerformancePriceDTO(): there is a body 'ID': {} ", performancePriceDTO.getId());
            throw new BadRequestException();
        }

        PerformancePrice performancePrice = new PerformancePrice();
        toPerformancePrice(performancePriceDTO, performancePrice);

        Performance performance = performanceService.getPerformance(performancePriceDTO.getPerformanceDTO().getId());
        performancePrice.setPerformance(performance);

        Section section = sectionService.getSection(performancePriceDTO.getSectionDTO().getId());
        performancePrice.setSection(section);

        performancePrice = performancePriceRepository.save(performancePrice);
        LOGGER.info("insertPerformancePriceDTO: You inserted successfully new PerformancePrice to: {}", performancePriceDTO);

        return toPerformancePriceDTO(performancePrice);
    }

    public PerformancePriceDTO updatePerformancePriceDTO(PerformancePriceDTO performancePriceDTO) throws Exception {
        PerformancePrice performancePrice = performancePriceRepository.findById(performancePriceDTO.getId()).orElseThrow(Exception::new);//not found

        toPerformancePrice(performancePriceDTO, performancePrice);
        performancePrice = performancePriceRepository.save(performancePrice);
        LOGGER.info("updatePerformancePriceDTO() You updated successfully PerformancePrice to: {}", performancePriceDTO);

        return toPerformancePriceDTO(performancePrice);
    }

    public void deletePerformancePrice(Long id) throws Exception {
        if (!performancePriceRepository.existsById(id)) {
            LOGGER.error("deletePerformancePrice(): PerformancePrice with 'ID' {} does not exist.", id);
            throw new Exception("NotFound");
        }

        performancePriceRepository.deleteById(id);
        LOGGER.error("deletePerformancePrice() You deleted successfully PerformancePrice with 'ID': {}", id);
    }

    private PerformancePriceDTO toPerformancePriceDTO(PerformancePrice performancePrice) {
        final PerformancePriceDTO performancePriceDTO = new PerformancePriceDTO();
        final PerformanceDTO performanceDTO = new PerformanceDTO();
        final SectionDTO sectionDTO = new SectionDTO();

        performanceDTO.setId(performancePrice.getPerformance().getId());
        performancePriceDTO.setPerformanceDTO(performanceDTO);

        sectionDTO.setId(performancePrice.getSection().getId());
        performancePriceDTO.setSectionDTO(sectionDTO);

        performancePriceDTO.setId(performancePrice.getId());
        performancePriceDTO.setTicketPrice(performancePrice.getTicketPrice());

        return performancePriceDTO;
    }

    private void toPerformancePrice(PerformancePriceDTO performancePriceDTO, PerformancePrice performancePrice) {
        performancePrice.setTicketPrice(performancePriceDTO.getTicketPrice());
    }

}
