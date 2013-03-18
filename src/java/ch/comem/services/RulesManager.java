/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Rule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Malo
 */
@Stateless
public class RulesManager implements RulesManagerLocal {
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;
    
        @Override
        public long createRule(String onEventType, int numberOfPoints, String badge) {
            Rule rule = new Rule();

            if(rule != null){

                rule.setOnEventType(onEventType);
                rule.setNumberOfPoints(numberOfPoints);
                rule.setBadge(badge);

                persist(rule);
                em.flush();
            }
            return rule.getId();

        }
        public void persist(Object object) {
            em.persist(object);
        }
        
}
