/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Application;
import java.util.List;
import javax.ejb.EJB;
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
    @EJB
    private BadgesManagerLocal bm;
    @EJB
    private RulesManagerLocal rm;

    @Override
    public long createApplication(String name, String description, String apiKey, String apiSecret) {
        Application application = new Application();
        application.setName(name);
        application.setDescription(description);
        application.setApiKey(apiKey);
        application.setApiSecret(apiSecret);
        
        persist(application);
        
        Long badge1Id = bm.createBadge("Newbie", 
                                       "Vous recevez ce badge automatiquement", 
                                       "badge_newbie");
        Long badge2Id = bm.createBadge("Amateur",
                                       "200 points atteints", 
                                       "badge_amateur");
        Long badge3Id = bm.createBadge("Pâtissier du dimanche", 
                                       "500 points atteints",
                                       "badge_pat_dim");
        Long badge4Id = bm.createBadge("Apprenti-pâtissier", 
                                       "900 points atteints", 
                                       "badege_app_pat");
        Long badge5Id = bm.createBadge("Pâtissier", 
                                       "1400 points atteints", 
                                       "badge_pat");
        Long badge6Id = bm.createBadge("Chef pâtissier", 
                                       "2000 points atteints", 
                                       "badge_chef_pat");
        Long badge7Id = bm.createBadge("Gâteaux", 
                                       "Vous recevez ce badge pour avoir publié au moins une recette dans la catégorie Gâteaux", 
                                       "badge_gateaux");
        Long badge8Id = bm.createBadge("Cupcakes", 
                                       "Vous recevez ce badge pour avoir publié au moins une recette dans la catégorie Cupcakes", 
                                       "badge_cupcakes");
        
        rm.createRule("Expérience acquise", 0, null, application.getId());
        rm.createRule("Expérience acquise", 200, badge2Id, application.getId());
        rm.createRule("Expérience acquise", 500, badge3Id, application.getId());
        rm.createRule("Expérience acquise", 900, badge4Id, application.getId());
        rm.createRule("Expérience acquise", 1400, badge5Id, application.getId());
        rm.createRule("Expérience acquise", 2000, badge6Id, application.getId());
        rm.createRule("Création de compte", 20, badge1Id, application.getId());
        rm.createRule("Publication Photo", 20, null, application.getId());
        rm.createRule("Publication Photo + Recette Complète", 50, null, application.getId());
        rm.createRule("Première publication", 40, null, application.getId());
        rm.createRule("5ème publication", 80, null, application.getId());
        rm.createRule("10ème publication", 120, null, application.getId());
        rm.createRule("Commenter une publication", 2, null, application.getId());
        rm.createRule("Liker une publication", 1, null, application.getId());
        rm.createRule("Gâteaux", 20, badge7Id, application.getId());
        rm.createRule("Cupcakes", 20, badge8Id, application.getId());

        em.flush();
        return application.getId();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Application> findAll() {
        List<Application> applications;
        applications = em.createNamedQuery("findAllApplications").getResultList();
        return applications;
    }

    

}
