/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.services.BadgesManagerLocal;
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
    private BadgesManagerLocal badgesManager;
    
    @Override
    public void testBadge(String name, String desc, String icon){
        for (int i = 0; i < 10; i++){
            long x = badgesManager.createBadge(name,desc, icon);
        }
    }
    
    

}
