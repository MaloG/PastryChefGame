/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.model.Rule;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Malo
 */
@Local
public interface RulesManagerLocal {

    long createRule(String onEventType, int numberOfPoints, long badge, long applicationId);

    List<Rule> findAll();
    
}
