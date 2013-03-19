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
public interface RulesManagerLocal {

    long createRule(String onEventType, int numberOfPoints, long badge, long applicationId);
    
}
