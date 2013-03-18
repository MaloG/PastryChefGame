/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Malo
 */
@Stateless
public class ApplicationsManager implements ApplicationsManagerLocal {
    @PersistenceContext(unitName = "Badge")
    private EntityManager em;

    @Override
    public long createApplication(String name, String description, String apiKey, String apiSecret) {
        Application application = new Application();
        
        application.setName(name);
        application.setDescription(description);
        application.setApiKey(apiKey);
        application.setApiSecret(apiSecret);
        
        persist(application);
        em.flush();
        
        return application.getId();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    

}
