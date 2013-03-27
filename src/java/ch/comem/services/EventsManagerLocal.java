/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Event;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface EventsManagerLocal {

    long createEvent(Long memberId, long applicationId, String type, 
                      long timeInMillis);

    public List<Event> findAll();

    public List<Event> getPlayerEvents(Long appId, Long playerId);
    
}
