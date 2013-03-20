/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.dto;

import ch.comem.model.Event;
import ch.comem.model.LeaderBoard;
import ch.comem.model.Player;
import ch.comem.model.Rule;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Malo
 */
public class ApplicationDTO {
    
    private Long id;

    private String name;
    private String description;
    private String apiKey;
    private String apiSecret;
    
    private List<Player> players = new LinkedList<Player>();
    private List<Event> events = new LinkedList<Event>();
    private List<Rule> rules = new LinkedList<Rule>();
    private LeaderBoard leaderBoard;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }
    
    
}
