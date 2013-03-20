/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.dto.ApplicationDTO;
import ch.comem.dto.EventDTO;
import ch.comem.dto.PlayerDTO;
import ch.comem.dto.RuleDTO;
import ch.comem.model.Application;
import ch.comem.model.Event;
import ch.comem.model.Player;
import ch.comem.model.Rule;
import ch.comem.services.ApplicationsManagerLocal;
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
@Path("ch.comem.model.application")
public class ApplicationFacadeREST extends AbstractFacade<Application> {
    @EJB
    private ApplicationsManagerLocal applicationsManager;
    
    
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;

    public ApplicationFacadeREST() {
        super(Application.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Application entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Application entity) {
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
    public ApplicationDTO find(@PathParam("id") Long id) {
        
        Application application =  super.find(id);
        ApplicationDTO applicationDTO = new ApplicationDTO();
            applicationDTO.setId(application.getId());
            applicationDTO.setName(application.getName());
            applicationDTO.setDescription(application.getDescription());
            applicationDTO.setApiKey(application.getApiKey());
            applicationDTO.setApiSecret(application.getApiSecret());
            
            List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
            for(Event event : application.getEvents()){
                EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTimestamp(event.getTimestamp());
                eventDTO.setType(event.getType());
                eventsDTO.add(eventDTO);
            }
            applicationDTO.setEvents(eventsDTO);
            
            List<PlayerDTO> playersDTO = new ArrayList<PlayerDTO>();
            for(Player player : application.getPlayers()){
                PlayerDTO playerDTO = new PlayerDTO();
                playerDTO.setFirstName(player.getFirstName());
                playerDTO.setLastName(player.getLastName());
                playerDTO.setEmail(player.getLastName());
                playersDTO.add(playerDTO);
            }
            applicationDTO.setPlayers(playersDTO);
            
            List<RuleDTO> rulesDTO = new ArrayList<RuleDTO>();
            for(Rule rule : application.getRules()){
                RuleDTO ruleDTO = new RuleDTO();
                ruleDTO.setId(rule.getId());
                ruleDTO.setOnEventType(rule.getOnEventType());
                ruleDTO.setNumberOfPoints(rule.getNumberOfPoints());
                rulesDTO.add(ruleDTO);
            }
            applicationDTO.setRules(rulesDTO);
            
        return applicationDTO;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<ApplicationDTO> findAll() {
        List<ApplicationDTO> applicationsDTO = new ArrayList<ApplicationDTO>();
        List<Application> applications = applicationsManager.findAll();
        
        for(Application application: applications ){
            ApplicationDTO applicationDTO = new ApplicationDTO();
            applicationDTO.setId(application.getId());
            applicationDTO.setName(application.getName());
            applicationDTO.setDescription(application.getDescription());
            applicationDTO.setApiKey(application.getApiKey());
            applicationDTO.setApiSecret(application.getApiSecret());
            
            List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
            for(Event event : application.getEvents()){
                EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTimestamp(event.getTimestamp());
                eventDTO.setType(event.getType());
                eventsDTO.add(eventDTO);
            }
            applicationDTO.setEvents(eventsDTO);
            
            List<PlayerDTO> playersDTO = new ArrayList<PlayerDTO>();
            for(Player player : application.getPlayers()){
                PlayerDTO playerDTO = new PlayerDTO();
                playerDTO.setFirstName(player.getFirstName());
                playerDTO.setLastName(player.getLastName());
                playerDTO.setEmail(player.getLastName());
                playersDTO.add(playerDTO);
            }
            applicationDTO.setPlayers(playersDTO);
            
            List<RuleDTO> rulesDTO = new ArrayList<RuleDTO>();
            for(Rule rule : application.getRules()){
                RuleDTO ruleDTO = new RuleDTO();
                ruleDTO.setId(rule.getId());
                ruleDTO.setOnEventType(rule.getOnEventType());
                ruleDTO.setNumberOfPoints(rule.getNumberOfPoints());
                rulesDTO.add(ruleDTO);
            }
            applicationDTO.setRules(rulesDTO);
            
            applicationsDTO.add(applicationDTO);
        }
        return applicationsDTO;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Application> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
