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
public interface ApplicationsManagerLocal {

    long createApplication(String name, String description, String apiKey, String apiSecret);
    
}
