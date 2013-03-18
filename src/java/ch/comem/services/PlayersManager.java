/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

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
    public long createPlayer(String firstName, String lastName, String email, int numberOfPoints) {
        Player player = new Player();
        
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        player.setNumberOfPoints(numberOfPoints);
        
        persist(player);
        em.flush();
        
        return player.getId();
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

}
