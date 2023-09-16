package gr.cinema.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class PerformanceStagingDTO {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String startTime;
    private PerformanceDTO performanceDTO;
    private RoomDTO roomDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
}
