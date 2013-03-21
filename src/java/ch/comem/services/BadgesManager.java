/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Badge;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Malo
 */
@Stateless
public class BadgesManager implements BadgesManagerLocal {
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public long createBadge(String name, String description, String icon){
        Badge badge = new Badge();
       
        badge.setName(name);
        badge.setDescription(description);
        badge.setIcon(icon);
        
        persist(badge);
        em.flush();
            
        return badge.getId();
    }
    
    @Override
    public List<Badge> findAll() {
        List<Badge> badges;
        badges = em.createNamedQuery("findAllBadges").getResultList();
        return badges;
    }

    @Override
    public List<Badge> findPlayerBadges(long playerId) {
        List<Badge> badges;
        badges = em.createNamedQuery("findPlayerBadges").setParameter("playerId", playerId).getResultList();
        return badges;
    }

}
