package gr.cinema.api.dto;

public class PerformancePriceDTO {

    private Long id;
    private Float ticketPrice;
    private PerformanceDTO performanceDTO;
    public SectionDTO sectionDTO;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public PerformanceDTO getPerformanceDTO() {
        return performanceDTO;
    }

    public void setPerformanceDTO(PerformanceDTO performanceDTO) {
        this.performanceDTO = performanceDTO;
    }

    public SectionDTO getSectionDTO() {
        return sectionDTO;
    }

    public void setSectionDTO(SectionDTO sectionDTO) {
        this.sectionDTO = sectionDTO;
    }
}
