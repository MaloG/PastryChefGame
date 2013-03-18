/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Badge;
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
    
    
    public long createBadge(String name, String description, String icon){
        Badge badge = new Badge();
       
        badge.setName(name);
        badge.setDescription(description);
        badge.setIcon(icon);
        
        em.persist(badge);
        em.flush();
        
        return badge.getId();
    }

    @Override
    public void businessMethod() {
    
    }
    

}
