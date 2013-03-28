/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import ch.comem.model.Badge;
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
    @PersistenceContext(unitName = "PastryChefGame")
    private EntityManager em;
    
    
    @Override
    public long createRule(String onEventType, int numberOfPoints, Long badgeId, 
                            Long applicationId) {
        Rule rule = new Rule();

        rule.setOnEventType(onEventType);
        rule.setNumberOfPoints(numberOfPoints);

        Application application = em.find(Application.class, applicationId);
        Badge badge = null;
        if (badgeId != null)
            badge = em.find(Badge.class, badgeId);
        if(application != null) {
            rule.setApplication(application);
            application.addRule(rule);
        }
        if(badge != null) {
            rule.setBadge(badge);
            badge.setRule(rule);
        }                
        
        persist(rule);
        em.flush();
        
        return rule.getId();

    }
        

    @Override
    public List<Rule> findAll() {
        List<Rule> list;
        list=em.createNamedQuery("findAllRules").getResultList();
        return list;
    }
        
    public void persist(Object object) {
        em.persist(object);
    }
}
