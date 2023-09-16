package gr.cinema.api.dto;

import java.time.LocalDate;

public class TicketDTO {

    private Long id;
    private LocalDate date;
    private Float price;
    private String rowss;
    private int seat;
    private String time;
    private PerformanceDTO performanceDTO;
    private RoomDTO roomDTO;
    private SectionDTO sectionDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getRowss() {
        return rowss;
    }

    public void setRowss(String rowss) {
        this.rowss = rowss;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PerformanceDTO getPerformanceDTO() {
        return performanceDTO;
    }

    public void setPerformanceDTO(PerformanceDTO performanceDTO) {
        this.performanceDTO = performanceDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public SectionDTO getSectionDTO() {
        return sectionDTO;
    }

    public void setSectionDTO(SectionDTO sectionDTO) {
        this.sectionDTO = sectionDTO;
    }
}
