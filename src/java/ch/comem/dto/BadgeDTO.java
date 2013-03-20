/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.dto;

import ch.comem.model.Rule;

/**
 *
 * @author raphaelbaumann
 */
public class BadgeDTO {
     private Long id;
    
    private String name;
    private String description;
    private String icon;
    // Pas de player car c'est un player qui accède à un badge et non le contraire
    private Rule rule;

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

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
    
    
}
