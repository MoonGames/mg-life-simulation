/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources.gui;

import java.util.ArrayList;
import sources.model.Cyte;

/**
 *
 * @author indy
 */
public interface GUIInterface {
    
    public void drawCytes(ArrayList<Cyte> cytes);
    
    public int getGUIWidht();
    
    public int getGUIHeight();
    
    public void showCount(int count);
    
    public void showMove(float move);
    
    public void showSurvival(float survival);
    
    public void showProliferate(float proliferate);
    
    public void showInvasiveness(float invassiveness);
    
    public void showTimeSpeed(float timeSpeed);
}
