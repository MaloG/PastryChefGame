package ch.comem.dto;

import ch.comem.model.Badge;
import ch.comem.model.Event;
import java.util.List;

/**
 *
 * @author raphaelbaumann
 */
public class PlayerDTO {
    
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }
    private String firstName;
    private String lastName;
    private String email;
    private Integer numberOfPoints;
    private List<BadgeDTO> badges;
    private List<EventDTO> events;

    public List<BadgeDTO> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeDTO> badges) {
        this.badges = badges;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
    
}
