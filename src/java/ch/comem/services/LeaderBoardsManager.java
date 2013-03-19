/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.LeaderBoard;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author Malo
 */
@Stateless
public class LeaderBoardsManager implements LeaderBoardsManagerLocal {
    private EntityManager em;

    @Override
    public long createLeaderBoard(String name, String description, long applicationId, List ranking) {
        
        LeaderBoard leaderboard = new LeaderBoard();
        leaderboard.setName(name);
        leaderboard.setDescription(description);
        Application application = em.find(Application.class, applicationId);
        // add ranking for players  for(i = 0; )
        if(application != null){
            
            leaderboard.setApplication(application);
            application.setLeaderBoard(leaderboard);
            
            persist(application);
            persist(leaderboard);
            em.flush();
        }
        return leaderboard.getId();
    }
    public void persist(Object object) {
        em.persist(object);
    }

}
