/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.dto.BadgeDTO;
import ch.comem.dto.EventDTO;
import ch.comem.dto.PlayerDTO;
import ch.comem.dto.RuleDTO;
import ch.comem.model.Badge;
import ch.comem.model.Event;
import ch.comem.model.Player;
import ch.comem.services.PlayersManagerLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author raphaelbaumann
 */
@Stateless
@Path("player")
public class PlayerFacadeREST extends AbstractFacade<Player> {
    @EJB
    private PlayersManagerLocal playersManager;
    
    @PersistenceContext(unitName = "PastryChefGame")
    private EntityManager em;

    public PlayerFacadeREST() {
        super(Player.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Player player) {
        playersManager.createPlayer(player.getFirstName(), 
                                    player.getLastName(), 
                                    player.getEmail(), 
                                    player.getApplication().getId(), 
                                    player.getMemberId());
        
    }


    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Player entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public PlayerDTO find(@PathParam("id") Long id) {

         PlayerDTO playerDTO = new PlayerDTO();
         Player player = super.find(id);
         playerDTO.setId(player.getId());
         playerDTO.setFirstName(player.getFirstName());
         playerDTO.setLastName(player.getLastName());
         playerDTO.setEmail(player.getEmail());
         playerDTO.setMemberId(player.getMemberId());
         
         List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
            for(Event event : player.getEvents()){
                EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTimeInMillis(event.getTimeInMillis());
                eventDTO.setType(event.getType());
                eventsDTO.add(eventDTO);
            }
            playerDTO.setEvents(eventsDTO);
            
            List<BadgeDTO> badgesDTO = new ArrayList<BadgeDTO>();
            for(Badge badge : player.getBadges()){
                BadgeDTO badgeDTO = new BadgeDTO();
                badgeDTO.setId(badge.getId());
                badgeDTO.setDescription(badge.getDescription());
                badgeDTO.setIcon(badge.getIcon());
                badgeDTO.setName(badge.getName());
                
                RuleDTO ruleDTO = new RuleDTO();
                
                ruleDTO.setId(badge.getRule().getId());
                ruleDTO.setNumberOfPoints(badge.getRule().getNumberOfPoints());
                ruleDTO.setOnEventType(badge.getRule().getOnEventType());
                
                badgeDTO.setRule(ruleDTO);
                
                badgesDTO.add(badgeDTO);
            }
        playerDTO.setBadges(badgesDTO);
         
        return playerDTO;
    }
    
    @GET
    @Path("badges/{playerId}")
    @Produces({"application/xml", "application/json"})
    public List<BadgeDTO> findBadges(@PathParam("playerId") Long playerId) {
        List<Badge> badges = playersManager.getPlayerBadges(playerId);
        List<BadgeDTO> badgesDTO = new ArrayList<BadgeDTO>();
        for(Badge badge : badges){
            BadgeDTO badgeDTO = new BadgeDTO();
            badgeDTO.setName(badge.getName());
            badgeDTO.setId(badge.getId());
            badgeDTO.setDescription(badge.getDescription());
            badgeDTO.setIcon(badge.getIcon());
            badgesDTO.add(badgeDTO);
        }
        return badgesDTO;
    }
    
    @GET
    @Path("points/{playerId}")
    @Produces({"application/xml", "application/json"})
    public PlayerDTO findPoints(@PathParam("playerId") Long playerId) {
        List<Player> players = playersManager.getPlayerPoints(playerId);
        PlayerDTO playerDTO = new PlayerDTO();
        for(Player player: players){
            playerDTO.setNumberOfPoints(player.getNumberOfPoints());
        }
        return playerDTO;
    }
    
    @GET
    @Path("memberPoints/{playerId}")
    @Produces({"application/xml", "application/json"})
    public PlayerDTO findMemberPoints(@PathParam("playerId") Long playerId) {
        long memberId = playersManager.findPlayerIdFromMemberId(playerId);
        List<Player> players = playersManager.getPlayerPoints(memberId);
        PlayerDTO playerDTO = new PlayerDTO();
        for(Player player: players){
            playerDTO.setNumberOfPoints(player.getNumberOfPoints());
        }
        return playerDTO;
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<PlayerDTO> findAll() {
         List<PlayerDTO> results = new ArrayList<PlayerDTO>();
         List<Player> players = playersManager.findAll();
         
         for(Player player : players){
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.setId(player.getId());
            playerDTO.setFirstName(player.getFirstName());
            playerDTO.setLastName(player.getLastName());
            playerDTO.setEmail(player.getLastName());
            
            List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
            for(Event event : player.getEvents()){
                EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTimeInMillis(event.getTimeInMillis());
                eventDTO.setType(event.getType());
                eventsDTO.add(eventDTO);
            }
            playerDTO.setEvents(eventsDTO);
            
            List<BadgeDTO> badgesDTO = new ArrayList<BadgeDTO>();
            for(Badge badge : player.getBadges()){
                BadgeDTO badgeDTO = new BadgeDTO();
                badgeDTO.setId(badge.getId());
                badgeDTO.setDescription(badge.getDescription());
                badgeDTO.setIcon(badge.getIcon());
                badgeDTO.setName(badge.getName());
                
                RuleDTO ruleDTO = new RuleDTO();
                
                ruleDTO.setId(badge.getRule().getId());
                ruleDTO.setNumberOfPoints(badge.getRule().getNumberOfPoints());
                ruleDTO.setOnEventType(badge.getRule().getOnEventType());
                
                badgeDTO.setRule(ruleDTO);
                
                badgesDTO.add(badgeDTO);
            }
            playerDTO.setBadges(badgesDTO);
            
            results.add(playerDTO);
         }
         
        return results;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Player> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
