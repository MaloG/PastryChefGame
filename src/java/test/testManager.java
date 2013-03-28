/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.services.ApplicationsManagerLocal;
import ch.comem.services.BadgesManagerLocal;
import ch.comem.services.EventsManagerLocal;
import ch.comem.services.LeaderBoardsManagerLocal;
import ch.comem.services.PlayersManagerLocal;
import ch.comem.services.RulesManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Malo
 */
@Stateless
@WebService
public class testManager implements testManagerLocal {
    @EJB
    private LeaderBoardsManagerLocal leaderBoardsManager;
    @EJB
    private RulesManagerLocal rulesManager;
    @EJB
    private ApplicationsManagerLocal applicationsManager;
    @EJB
    private EventsManagerLocal eventsManager;
    @EJB
    private PlayersManagerLocal playersManager;
    @EJB
    private BadgesManagerLocal badgesManager;
    
    //application
    private String appName = "PastryChef";
    private String apiKey = "12431423";
    private String apiSecret = "987654";
    private String appDesc = "pastry sharing application";
    

    /** Method which enable the creation of the PastryChefGamification 
     * application. This method also create a set of rules and badges involved 
     * in this application.
     * 
     */
    @Override
    public void createApplication() {
        applicationsManager.createApplication(appName, appDesc, apiKey, apiSecret);
    }
    
}
