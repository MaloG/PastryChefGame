/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
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
        public long createRule(String onEventType, int numberOfPoints, String badge, long applicationId) {
            Rule rule = new Rule();
            

            rule.setOnEventType(onEventType);
            rule.setNumberOfPoints(numberOfPoints);
            rule.setBadge(badge);
            
            Application application = em.find(Application.class, applicationId);
            
            if(application != null){
                rule.setApplication(application);
                
                persist(rule);
                em.flush();
            }
            return rule.getId();

        }
        public void persist(Object object) {
            em.persist(object);
        }
        
}
