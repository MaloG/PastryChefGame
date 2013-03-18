/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Player;
import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface EventsManagerLocal {

    long createEvent(long playerId, String type, long applicationId);
    
}
