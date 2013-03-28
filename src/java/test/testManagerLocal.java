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

    /** Method to be overridden which enable the creation of the 
     * PastryChefGamification application.
     * 
     */
    void createApplication();
}
