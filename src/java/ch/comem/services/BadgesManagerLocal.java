/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface BadgesManagerLocal {

    void businessMethod();
    
    long createBadge(String name, String description, String icon);
    
}