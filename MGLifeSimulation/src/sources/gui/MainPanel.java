/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import sources.model.Cyte;

/**
 *
 * @author indy
 */
public class MainPanel extends JPanel implements GUIInterface {

    protected CytePoint[] cytePoints = new CytePoint[1];
    protected int cytePointsStop = 0;
    protected int cyteSize = 5;

    public MainPanel() {
    }

    @Override
    public void paintComponent(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawCytesLocal(g);
    }

    protected void drawCytesLocal(Graphics2D g) {
            for (int i = 0; i < cytePointsStop; i++) {
                cytePoints[i].draw(g);
            }
    }

    protected void setSytePointLength(int length) {
        int nl = cytePoints.length;
        if (length > cytePoints.length) {
            if (nl == 0) {
                nl = 1;
            }
            while (nl < length) {
                nl *= 2;
            }
            cytePoints = new CytePoint[nl];
        } else if (length < (cytePoints.length / 2)) {
            while ((nl / 2) > length) {
                nl /= 2;
            }
            cytePoints = new CytePoint[nl];
        }
        cytePointsStop = length;
    }

    @Override
    public void drawCytes(ArrayList<Cyte> cytes) {
        setSytePointLength(cytes.size());
        for (int i = 0; i < cytes.size(); i++) {
            Cyte c = cytes.get(i);
            cytePoints[i] = new CytePoint(cyteSize, generateCyteColor(c), new Point(c.getPositionX(), c.getPositionY()));
        }
    }

    protected Color generateCyteColor(Cyte c) {
        int r = (int) (c.getInvasivenessCoefficient() * 255f);
        int b = (int) (c.getProliferateCoefficient() * 255f);
        int g = (int) (c.getSurvivalCoefficient() * 255f);
        return new Color(r, g, b);
    }

    @Override
    public int getGUIWidht() {
        return getWidth();
    }

    @Override
    public int getGUIHeight() {
        return getHeight();
    }

    @Override
    public void showCount(int count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showMove(float move) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showSurvival(float survival) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showProliferate(float proliferate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showInvasiveness(float invassiveness) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showTimeSpeed(float timeSpeed) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
