/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Badge;
import ch.comem.model.Player;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Malo
 */
@Stateless
public class PlayersManager implements PlayersManagerLocal {
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;

    
    @Override
    public long createPlayer(String firstName, String lastName, String email, int numberOfPoints, long applicationId) {
        Player player = new Player();
        
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        player.setNumberOfPoints(numberOfPoints);
        
        Application application = em.find(Application.class, applicationId);
        
        if(application != null){
            
            player.setApplication(application);
            application.addPlayers(player);
            
            persist(application);
            persist(player);
            em.flush();
        }
        return player.getId();
    }

    @Override
    public void addBadgeToPlayer(long badgeId, long playerId) {
        
        Player player = em.find(Player.class, playerId);
        Badge badge = em.find(Badge.class, badgeId);
        
        if(player != null && badge != null){
            
            player.addBadges(badge);

            em.persist(badge);
            em.persist(player);
            em.flush();
        };
        
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

    
}
