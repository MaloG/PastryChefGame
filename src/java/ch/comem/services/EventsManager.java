/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Event;
import ch.comem.model.Player;
import javax.ejb.Stateless;

/**
 *
 * @author Malo
 */
@Stateless
public class EventsManager implements EventsManagerLocal {

    @Override
    public long createEvent(Player player, String type, Application application, int timestamp) {
        Event event = new Event();
        return event.getId();
    }
    
    

}
