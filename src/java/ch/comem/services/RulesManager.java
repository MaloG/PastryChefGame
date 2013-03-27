/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Badge;
import ch.comem.model.Event;
import ch.comem.model.Rule;
import java.util.List;
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
        public long createRule(Long eventId, int numberOfPoints, long badgeId, long applicationId) {
            Rule rule = new Rule();
            
            Event event = em.find(Event.class, eventId);
            if(event != null){
                
            }
            //rule.setOnEventType(onEventType);
            rule.setNumberOfPoints(numberOfPoints);
            
            Application application = em.find(Application.class, applicationId);
            Badge badge;
            badge = em.find(Badge.class, badgeId);
            
            if(application != null && badge != null){
                
                rule.setApplication(application);
                application.addRule(rule);
                
                rule.setBadge(badge);
                badge.setRule(rule);
                
                persist(rule);
                em.flush();
            }
            return rule.getId();

        }
        public void persist(Object object) {
            em.persist(object);
        }

    @Override
    public List<Rule> findAll() {
        List<Rule> list;
        list=em.createNamedQuery("findAllRules").getResultList();
        return list;
    }
        
}
