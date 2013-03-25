/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface testManagerLocal {
    
    public void createBadge();

    void createPlayer();

    void createEvents();

    void createApplication();

    void doAll();

    void createRules();

    void createLeaderBoards();

    void minSetUp();
}
