/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import javax.ejb.Local;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Malo
 */
@Local
public interface ApplicationsManagerLocal {
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    long createApplication(String name, String description, String apiKey, String apiSecret);
    
}
