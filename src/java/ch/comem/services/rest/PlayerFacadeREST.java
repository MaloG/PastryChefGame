/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.dto.PlayerDTO;
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
@Path("ch.comem.model.player")
public class PlayerFacadeREST extends AbstractFacade<Player> {
    @EJB
    private PlayersManagerLocal playersManager;
    
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;

    public PlayerFacadeREST() {
        super(Player.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Player entity) {
        super.create(entity);
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

         PlayerDTO result = new PlayerDTO();
         Player player = super.find(id);
         result.setFirstName(player.getFirstName());
         result.setLastName(player.getLastName());
         result.setBadges(player.getBadges());
         result.setEmail(player.getEmail());
         result.setEvents(player.getEvent());

        return result;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<PlayerDTO> findAll() {
         List<PlayerDTO> results = new ArrayList<PlayerDTO>();
         List<Player> players = playersManager.findAll();
         for(int i = 0; i < players.size(); i++){
            PlayerDTO playerResult = new PlayerDTO();
            playerResult.setFirstName(players.get(i).getFirstName());
            playerResult.setLastName(players.get(i).getLastName());
            playerResult.setBadges(players.get(i).getBadges());
            playerResult.setEmail(players.get(i).getEmail());
            playerResult.setEvents(players.get(i).getEvents());
            results.add(playerResult);
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
