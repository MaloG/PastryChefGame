/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.dto;

/**
 *
 * @author Malo
 */
public class RuleDTO {
    
    private Long id;
    private String onEventType;
    private Integer numberOfPoints;
    
    // Pas de bage car c'est un bage qui accède à une régle et non le contraire
    // Pas d'application car c'est l'application qui accède à une régle et non le contraire
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOnEventType() {
        return onEventType;
    }

    public void setOnEventType(String onEventType) {
        this.onEventType = onEventType;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
    
    
}
