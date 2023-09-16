package gr.cinema.api.dto;

public class PerformanceDTO {

    private Long id;
    private String actors;
    private String category;
    private String costumes;
    private String direction;
    private int duration;
    private String title;
    private String production;
    private String stars;
    private String summary;
    private Boolean toStage;
    private String trailer;
    private UserDTO userDTO;
    private TicketDTO ticketDTO;
    private PerformanceStagingDTO performanceStagingDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public TicketDTO getTicketDTO() {
        return ticketDTO;
    }

    public void setTicketDTO(TicketDTO ticketDTO) {
        this.ticketDTO = ticketDTO;
    }

    public PerformanceStagingDTO getPerformanceStagingDTO() {
        return performanceStagingDTO;
    }

    public void setPerformanceStagingDTO(PerformanceStagingDTO performanceStagingDTO) {
        this.performanceStagingDTO = performanceStagingDTO;
    }
}
