/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface LeaderBoardsManagerLocal {

    long createLeaderBoard(String name, String description, long applicationId);
    
}
