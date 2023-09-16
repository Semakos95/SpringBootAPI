package gr.cinema.api.entity;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class PerformanceStaging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "date")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String startTime;

    @ManyToOne
    @JoinColumn(name = "performance_id", referencedColumnName = "id")
    private Performance performance;
    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id",nullable = false)
    private Room room;

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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
}
