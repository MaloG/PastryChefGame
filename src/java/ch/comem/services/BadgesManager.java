/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Badge;
import ch.comem.model.Rule;
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
    public long createBadge(String name, String description, String icon,long RulesId){
        Badge badge = new Badge();
       
        badge.setName(name);
        badge.setDescription(description);
        badge.setIcon(icon);
        
        persist(badge);
        em.flush();
            
        return badge.getId();
    }

    @Override
    public void businessMethod() {
    
    }
    

}
