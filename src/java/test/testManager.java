/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.services.BadgesManagerLocal;
import ch.comem.services.PlayersManagerLocal;
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
    private PlayersManagerLocal playersManager;
    @EJB
    private BadgesManagerLocal badgesManager;
    
    private String[] firstNames = {"Paul", "Arthur", "Danielle", "Georges",
                                   "Isabelle", "Chuck", "Mario", "Joëlle",
                                   "Léa", "Walter"};
    private String[] lastNames = {"Hogan", "Lion", "Müller", "Pasche",
                                  "Adjani", "Norris", "Kart", "Wyss",
                                  "Teoni", "Baertchi"};
    private String[] ages = {"fsdf", "fsdf", "fsdf", "fsdf", "fsdf", "fsdf", "fsdf", "fsdf", "fsdf", "fsdf"};
    
    private String[] names = {"newbie","apprenti-patissier","chef-patissier"};
    private String[] descriptions={"blablabla","blablabla","blablabla"};
    private String[] icones={"http://newbie.com","http://ap.com","http://cp.com"};
    
    private long[] badgeLing={0,1,2,2,1,0,0,1,2,2};
    
    @Override
    public void testBadge(){
        for (int i = 0; i < 3; i++){
            long x = badgesManager.createBadge(names[i],descriptions[i], icones[i]);
        }
    
    }

    @Override
    public void createPlayer() {
        for (int index = 0; index < 10; index++) {
            long x = playersManager.createPlayer(firstNames[index], lastNames[index], ages[index], index);
            System.out.println(x);
        }
    }
    
    public void giveBadgeToPlayer(){
//        createPlayer();
//        testBadge();
        for (int index = 0; index < 10; index++) {
            playersManager.addBadgeToPlayer(badgeLing[index], (long) index);
        }
    }
    
    
    
    

}
