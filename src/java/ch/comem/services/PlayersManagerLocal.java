/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface PlayersManagerLocal {


    long createPlayer(String firstName, String lastName, String email, int numberOfPoints, long applicationId);

    void addBadgeToPlayer(long badgeId, long playerId);

    public void persist(java.lang.Object object);
    
}
