/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.dto.BadgeDTO;
import ch.comem.model.Badge;
import ch.comem.services.BadgesManagerLocal;
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
@Path("badge")
public class BadgeFacadeREST extends AbstractFacade<Badge> {
    @EJB
    private BadgesManagerLocal badgesManager;
    
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;

    public BadgeFacadeREST() {
        super(Badge.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Badge entity) {
        badgesManager.createBadge(entity.getName(), entity.getDescription(), entity.getIcon());
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Badge entity) {
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
    public BadgeDTO find(@PathParam("id") Long id) {
        
        BadgeDTO badgeDTO = new BadgeDTO();
        Badge badge = super.find(id);
        badgeDTO.setId(badge.getId());
        badgeDTO.setName(badge.getName());
        badgeDTO.setDescription(badge.getDescription());
        badgeDTO.setIcon(badge.getIcon());
        //result.setRule(badge.getRule());
        
        return badgeDTO;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<BadgeDTO> findAll() {
        List<BadgeDTO> results = new ArrayList<BadgeDTO>();
        List<Badge> badges = badgesManager.findAll();
        
        for(Badge badge : badges){
        
            BadgeDTO badgeDTO = new BadgeDTO();
            badgeDTO.setId(badge.getId());
            badgeDTO.setName(badge.getName());
            badgeDTO.setDescription(badge.getDescription());
            badgeDTO.setIcon(badge.getIcon());
            //badgeDTO.setRule(badge.getRule());
            
            results.add(badgeDTO);
        }
        return results;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Badge> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
