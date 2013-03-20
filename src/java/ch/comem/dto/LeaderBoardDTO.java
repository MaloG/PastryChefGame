/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.dto;

import java.util.List;

/**
 *
 * @author Malo
 */
public class LeaderBoardDTO {
    
    private Long id;
    private String name;
    private String description;

    //private List<Integer> rankingScore;
    //private List<Long> rankingPlayer;
    
    // Pas d'application car c'est l'application qui accède au leaderboard et non le contraire

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
    
    
}
