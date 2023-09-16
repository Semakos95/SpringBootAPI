package gr.cinema.api.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long rowss;
    private int seats;

    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id",nullable = false)
    private Room room;

    @OneToMany (mappedBy = "section")
    private List<PerformancePrice> performancePrice;

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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<PerformancePrice> getPerformancePrice() {
        return performancePrice;
    }

    public void setPerformancePrice(List<PerformancePrice> performancePrice) {
        this.performancePrice = performancePrice;
    }
}
