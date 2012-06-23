/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author indy
 */
public class CytePoint {

    protected int size = 2;
    protected Color color = Color.RED;
    protected Point position = new Point();

    public CytePoint(int size, Color color, Point position) {
        this.size = size;
        this.color = color;
        this.position = position;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(position.x - (size / 2), position.y - (size / 2), size, size);
    }
}
