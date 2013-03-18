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
    public void testBadge(String name, String desc, String icon);
}
