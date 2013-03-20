/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Malo
 */
@XmlRootElement
public class EventDTO implements Serializable{
    
    private Long id;
    private String type;
    private Timestamp timestamp;
    
    // Pas d'application car c'est l'application qui accède à un event et non le contraire
    // Pas de player car c'est un player qui a fait un event et non le contraire
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
