package gr.cinema.api.dto;

public class SectionDTO {

    private Long id;
    private String name;
    private Long rowss;
    private int seats;
    private RoomDTO roomDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRowss() {
        return rowss;
    }

    public void setRowss(Long rowss) {
        this.rowss = rowss;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }
}
