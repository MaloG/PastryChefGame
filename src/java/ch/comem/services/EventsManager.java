/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Event;
import ch.comem.model.Player;
import java.sql.Timestamp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Malo
 */
@Stateless
public class EventsManager implements EventsManagerLocal {
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;
    
    @Override
    public long createEvent(long playerId, String type, long applicationId) {
        Timestamp timestamp = new Timestamp(1000000);
        Event event = new Event();
        Player player = em.find(Player.class, playerId);
        Application application = em.find(Application.class, applicationId);
        
        if(player != null && application != null){
        
            event.setPlayer(player);
            event.setType(type);
            event.setTimestamp(timestamp);

            event.setApplication(application);

            persist(event);
            em.flush();
        }
        return event.getId();
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    

}
