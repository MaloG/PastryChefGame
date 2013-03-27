/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.dto.EventDTO;
import ch.comem.dto.RuleDTO;
import ch.comem.model.Event;
import ch.comem.model.Rule;
import ch.comem.services.RulesManagerLocal;
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
@Path("rule")
public class RuleFacadeREST extends AbstractFacade<Rule> {
    @EJB
    private RulesManagerLocal rulesManager;
    
    
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;

    public RuleFacadeREST() {
        super(Rule.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Rule entity) {
        rulesManager.createRule(entity.getOnEventType().getId(), entity.getNumberOfPoints(), entity.getBadge().getId(), entity.getApplication().getId());
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Rule entity) {
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
    public RuleDTO find(@PathParam("id") Long id) {
        
        RuleDTO ruleDTO = new RuleDTO();
        Rule rule = super.find(id);
        ruleDTO.setId(rule.getId());
        ruleDTO.setNumberOfPoints(rule.getNumberOfPoints());
        
        Event event = rule.getOnEventType();
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTimestamp(event.getTimestamp());
        eventDTO.setType(event.getType());
        ruleDTO.setOnEventType(eventDTO);
       

        return ruleDTO;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<RuleDTO> findAll() {
        List<RuleDTO> results = new ArrayList<RuleDTO>();
        List<Rule> rules = rulesManager.findAll();
        
        for(Rule rule : rules){
            RuleDTO ruleDTO = new RuleDTO();
            ruleDTO.setId(rule.getId());
            ruleDTO.setNumberOfPoints(rule.getNumberOfPoints());
            
            Event event = rule.getOnEventType();
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(event.getId());
            eventDTO.setTimestamp(event.getTimestamp());
            eventDTO.setType(event.getType());
            ruleDTO.setOnEventType(eventDTO);
   
            results.add(ruleDTO);
        }     
        return results;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Rule> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
