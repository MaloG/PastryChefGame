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
public interface LeaderBoardsManagerLocal {

    void createLeaderBoard(String name, String description, long applicationId);
    
}
