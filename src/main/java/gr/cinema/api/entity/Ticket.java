package gr.cinema.api.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Float price;
    private String rowss;
    private int seat;
    private String time;

    @ManyToOne
    @JoinColumn(name = "performance_id", referencedColumnName = "id", nullable = false)
    private Performance performance;
    @OneToOne(mappedBy = "ticket") //mappedBy = enter name of the object class that belongs  //slave
    private OnlineTicket onlineTicket;
    @ManyToOne
    @JoinColumn(name = "section_id",referencedColumnName = "id",nullable = false)
    private Section section;
    @ManyToOne
    @JoinColumn(name= "room_id",referencedColumnName = "id", nullable = false)
    private Room room;

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

    public OnlineTicket getOnlineTicket() {
        return onlineTicket;
    }

    public void setOnlineTicket(OnlineTicket onlineTicket) {
        this.onlineTicket = onlineTicket;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
