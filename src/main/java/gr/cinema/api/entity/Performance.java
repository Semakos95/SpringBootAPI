package gr.cinema.api.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actors;
    private String category;
    private String costumes;
    private String direction;
    private int duration;
    private String title; //title
    private String production;
    private String stars;
    private String summary;
    private Boolean toStage;
    private String trailer;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToOne(mappedBy = "performance")
    private Image image;
    @ManyToOne
    @JoinColumn(name = "performance_staging_id",referencedColumnName = "id", nullable = true)
    private PerformanceStaging performanceStaging;
    @ManyToOne
    @JoinColumn(name = "performancePrice_id")
    private PerformancePrice performancePrice;
    @OneToMany(mappedBy = "performance")
    private List<Ticket> tickets;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCostumes() {
        return costumes;
    }

    public void setCostumes(String costumes) {
        this.costumes = costumes;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getToStage() {
        return toStage;
    }

    public void setToStage(Boolean toStage) {
        this.toStage = toStage;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public PerformanceStaging getPerformanceStaging() {
        return performanceStaging;
    }

    public void setPerformanceStaging(PerformanceStaging performanceStaging) {
        this.performanceStaging = performanceStaging;
    }

    public PerformancePrice getPerformancePrice() {
        return performancePrice;
    }

    public void setPerformancePrice(PerformancePrice performancePrice) {
        this.performancePrice = performancePrice;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
