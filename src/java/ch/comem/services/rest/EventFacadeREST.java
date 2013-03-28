/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.dto.EventDTO;
import ch.comem.model.Event;
import ch.comem.services.EventsManagerLocal;
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
@Path("event")
public class EventFacadeREST extends AbstractFacade<Event> {
    @EJB
    private EventsManagerLocal eventsManager;
    
    @PersistenceContext(unitName = "PastryChefGame")
    private EntityManager em;
    
    public EventFacadeREST() {
        super(Event.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Event entity) {
        eventsManager.createEvent(entity.getPlayer().getMemberId(), 
                                  entity.getApplication().getId(), 
                                  entity.getType(), entity.getTimeInMillis());
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Event entity) {
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
    public EventDTO find(@PathParam("id") Long id) {
        Event event = super.find(id);
        EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTimeInMillis(event.getTimeInMillis());
                eventDTO.setType(event.getType());
                
        return eventDTO;
    }
    
    @GET
    @Path("searchByPlayer/{playerId}/{appId}")
    @Produces({"application/xml", "application/json"})
    public List<EventDTO> find(@PathParam("playerId") Long playerId, @PathParam("appId") Long appId) {
        
        List<Event> events = eventsManager.getPlayerEvents(appId, playerId);
        List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
            for(Event event : events){
                EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTimeInMillis(event.getTimeInMillis());
                eventDTO.setType(event.getType());
                eventsDTO.add(eventDTO);
            }

        return eventsDTO;
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<EventDTO> findAll() {
        
        List<Event> events = eventsManager.findAll();
        List<EventDTO> eventsDTO = new ArrayList<EventDTO>();
            for(Event event : events){
                EventDTO eventDTO = new EventDTO();
                eventDTO.setId(event.getId());
                eventDTO.setTimeInMillis(event.getTimeInMillis());
                eventDTO.setType(event.getType());
                eventsDTO.add(eventDTO);
            }
            
        return eventsDTO;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Event> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
