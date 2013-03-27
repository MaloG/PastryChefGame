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
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    
    //Player
    private String[] firstNames = {"Paul", "Arthur", "Danielle", "Georges",
                                   "Isabelle", "Chuck", "Mario", "Joëlle",
                                   "Léa", "Walter"};
    private String[] lastNames = {"Hogan", "Lion", "Müller", "Pasche",
                                  "Adjani", "Norris", "Kart", "Wyss",
                                  "Teoni", "Baertchi"};
    private String[] ages = {"fsdf", "fsdf", "fsdf", "fsdf", "fsdf", "fsdf",
                             "fsdf", "fsdf", "fsdf", "fsdf"};
    
    private long[] memberId = {1,2,3,4,5,6,7,8,9,10};
    private long[] appId = {1,1,1,1,1,1,1,1,1,1};
    //application
    private String[] appName = {"PastryChef","otherApp"};
    private String[] apiKey = {"12431423","324423512"};
    private String[] apiSecret = {"987654","12765432"};
    private String[] appDesc = {"pastry sharing application", "other useless application you'll never use","something else"};
    

    @Override
    public void createPlayer() {
        for (int index = 0; index < 10; index++) {
            long x = playersManager.createPlayer(firstNames[index], lastNames[index], ages[index], appId[index], memberId[index]);
            System.out.println(x);
        }
    }

    @Override
    public void createApplication() {
        for(int i = 0; i < 2; i++){
            applicationsManager.createApplication(appName[i], appDesc[i], apiKey[i], apiSecret[i]);
        }
    }
    
    @Override
    public void createLeaderBoards() {
        leaderBoardsManager.createLeaderBoard("stuff", "long stuff", 1);
    }

    @Override
    public void doAll() {
        createApplication();
        createPlayer();
        createLeaderBoards();
    }

    @Override
    public void minSetUp() {
        createApplication();
    }

  

}
