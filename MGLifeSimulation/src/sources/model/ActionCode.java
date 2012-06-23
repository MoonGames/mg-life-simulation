/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources.model;

/**
 *
 * @author indy
 */
public class ActionCode {

    public static final int ACTION_DEATH = 0;
    public static final int ACTION_PROLIFERATE = 1;
    protected int action = 0;

    public ActionCode(int actoin) {
        this.action = actoin;
    }

    public int getAction() {
        return action;
    }
}
