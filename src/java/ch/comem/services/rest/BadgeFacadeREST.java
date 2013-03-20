/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.model.Badge;
import ch.comem.services.BadgesManagerLocal;
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
@Path("ch.comem.model.badge")
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
        super.create(entity);
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
    public Badge find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Badge> findAll() {
        return badgesManager.findAll();
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
