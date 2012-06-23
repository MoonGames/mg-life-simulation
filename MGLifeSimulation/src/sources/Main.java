/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import sources.gui.MainFrame;
import sources.model.TheUniverse;

/**
 *
 * @author indy
 */
public class Main {

    public static void main(String args[]) {
        System.out.println("The best simulation ever!");
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        TheUniverse universe = new TheUniverse(mf);
        mf.setUniverseExtraInterface(universe);
        universe.restartLife(5);
    }
}
