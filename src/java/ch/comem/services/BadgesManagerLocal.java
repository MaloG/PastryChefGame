/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Badge;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface BadgesManagerLocal {
    
    long createBadge(String name, String description, String icon);

    public List<Badge> findAll();

    List<Badge> findPlayerBadges(long playerId);
    
}
