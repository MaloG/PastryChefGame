/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.dto;

import ch.comem.model.Rule;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raphaelbaumann
 */
@XmlRootElement
public class BadgeDTO implements Serializable{
     private Long id;
    
    private String name;
    private String description;
    private String icon;
    // Pas de player car c'est un player qui accède à un badge et non le contraire
    private RuleDTO rule;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }  
    
    public RuleDTO getRule() {
        return rule;
    }

    public void setRule(RuleDTO rule) {
        this.rule = rule;
    }
    
}
