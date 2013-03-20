package ch.comem.dto;

import ch.comem.model.Badge;
import ch.comem.model.Event;
import java.util.List;

/**
 *
 * @author raphaelbaumann
 */
public class PlayerDTO {
    
    private String firstName;
    private String lastName;
    private String email;
    private Integer numberOfPoints;
    private List<Badge> badges;
    // Pas d'application car c'est l'application qui possède des player et non le contraire
    private List<Event> evenements;

    public List<Event> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Event> evenements) {
        this.evenements = evenements;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
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
