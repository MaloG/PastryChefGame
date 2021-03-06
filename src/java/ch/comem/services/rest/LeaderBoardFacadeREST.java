/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services.rest;

import ch.comem.dto.LeaderBoardDTO;
import ch.comem.model.LeaderBoard;
import ch.comem.services.LeaderBoardsManagerLocal;
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
@Path("leaderboard")
public class LeaderBoardFacadeREST extends AbstractFacade<LeaderBoard> {
    @EJB
    private LeaderBoardsManagerLocal leaderBoardsManager;
    
    @PersistenceContext(unitName = "PastryChefGame")
    private EntityManager em;

    public LeaderBoardFacadeREST() {
        super(LeaderBoard.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(LeaderBoard entity) {
        leaderBoardsManager.createLeaderBoard(entity.getName(), entity.getDescription(), entity.getApplication().getId());
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(LeaderBoard entity) {
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
    public LeaderBoardDTO find(@PathParam("id") Long id) {
        
        LeaderBoardDTO leaderBoardDTO = new LeaderBoardDTO();
        LeaderBoard leaderboard = super.find(id);
        leaderBoardDTO.setId(leaderboard.getId());
        leaderBoardDTO.setName(leaderboard.getName());
        leaderBoardDTO.setDescription(leaderboard.getDescription());
        
        return leaderBoardDTO;
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<LeaderBoardDTO> findAll() {
        List<LeaderBoardDTO> results = new ArrayList<LeaderBoardDTO>();
        List<LeaderBoard> leaderBoards = leaderBoardsManager.findAll();
        
        for(LeaderBoard leaderBoard : leaderBoards){
            LeaderBoardDTO leaderBoardDTO = new LeaderBoardDTO();
            leaderBoardDTO.setId(leaderBoard.getId());
            leaderBoardDTO.setName(leaderBoard.getName());
            leaderBoardDTO.setDescription(leaderBoard.getDescription());
            
            results.add(leaderBoardDTO);
        }
        
        return results;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<LeaderBoard> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
