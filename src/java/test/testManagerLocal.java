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

    void createPlayer();

    void createApplication();

    void doAll();

    void createLeaderBoards();

    void minSetUp();
}
