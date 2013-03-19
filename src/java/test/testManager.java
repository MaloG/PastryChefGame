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
    
    //Player
    private String[] firstNames = {"Paul", "Arthur", "Danielle", "Georges",
                                   "Isabelle", "Chuck", "Mario", "Joëlle",
                                   "Léa", "Walter"};
    private String[] lastNames = {"Hogan", "Lion", "Müller", "Pasche",
                                  "Adjani", "Norris", "Kart", "Wyss",
                                  "Teoni", "Baertchi"};
    private String[] ages = {"fsdf", "fsdf", "fsdf", "fsdf", "fsdf", "fsdf",
                             "fsdf", "fsdf", "fsdf", "fsdf"};
    
    //badge
    private String[] names = {"newbie","apprenti-patissier","chef-patissier"};
    private String[] descriptions={"blablabla","blablabla","blablabla"};
    private String[] icones={"http://newbie.com","http://ap.com","http://cp.com"};
    //link badge and players
    private long[] badgeLing={3,1,2,2,1,3,3,1,2,2};
    //events
    private String[] eventType={"xp up","xp up","specific type","xp up","xp up",
                                "xp up","xp up","specific type","xp up","xp up",
                                "xp up","xp up","specific type","xp up","xp up",
                                "xp up","xp up","specific type","xp up","xp up"};
    private int[] playerId = {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10};
    private long[] appId = {1,2,1,1,1,1,2,2,1,2,1,2,1,2,1,1,2,1,1,2,2,1,1,2,2,2,1};
    //application
    private String[] appName = {"PastryChef","otherApp"};
    private String[] apiKey = {"12431423","324423512"};
    private String[] apiSecret = {"987654","12765432"};
    private String[] appDesc = {"pastry sharing application", "other useless application you'll never use","something else"};
    
    //Rules
    private String[] rules = {"5 patisserie","5 gateau","10 patisserie", "10 cupcake", "100 patisserie"};
    private int[] points = {50,50,50,100,200};
    private long[] badge = {1,2,3,1,2};
    
    
    @Override
    public void createBadge(){
        for (int i = 0; i < 3; i++){
            long x = badgesManager.createBadge(names[i],descriptions[i], icones[i]);
        }
    
    }

    @Override
    public void createPlayer() {
        for (int index = 0; index < 10; index++) {
            long x = playersManager.createPlayer(firstNames[index], lastNames[index], ages[index], index, appId[index]);
            System.out.println(x);
        }
    }
    
    public void giveBadgeToPlayer(){
        for (int index = 0; index < 10; index++) {
            playersManager.addBadgeToPlayer(badgeLing[index], (long) index);
        }
    }

    @Override
    public void createEvents() {
        for(int i = 0; i < 20; i++){
            eventsManager.createEvent(playerId[i], eventType[i], appId[i]);
        }
    }

    @Override
    public void createApplication() {
        for(int i = 0; i < 2; i++){
            applicationsManager.createApplication(appName[i], appDesc[i], apiKey[i], apiSecret[i]);
        }
    }
    
    @Override
    public void createRules() {
        for(int i = 0; i < 5; i++){
            rulesManager.createRule(rules[i], points[i], badge[i], 1);
        }
    }
    
    @Override
    public void createLeaderBoards() {
        leaderBoardsManager.createLeaderBoard("stuff", "long stuff", 2, null);
    }

    @Override
    public void doAll() {
        createApplication();
        createBadge();
        createRules();
        createPlayer();
        giveBadgeToPlayer();
        createEvents();
        
    }

  

}
