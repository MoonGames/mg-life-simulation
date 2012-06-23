/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sources.gui.GUIInterface;

/**
 *
 * @author indy
 */
public class TheUniverse extends Thread implements UniverseInterface, UniverseExtraInterface {

    protected GUIInterface gui = null;
    protected volatile boolean running = false;
    /**
     * updates per seconds
     */
    protected int ups = 20;
    protected int width = 100;
    protected int height = 100;
    protected float mutagenicCoefficient = 0.005f;
    protected float timeSpeed = 1f;
    protected float bestProliferateCoefficient = 1f;
    protected float bestSurvivalCoefficient = 1f;
    protected float bestInvasivenessCoefficient = 1f;
    protected float bestMobilityCoefficient = 1f;
    protected float maximalMoovingSpeed = 2f;
    protected int maxCytesCount = 100000;
    protected ArrayList<Cyte> cytes = new ArrayList<Cyte>();
    protected float time = 0f;

    public TheUniverse(GUIInterface gui) {
        this.gui = gui;
        updateSize();
        start();
    }

    protected void updateSize() {
        width = gui.getGUIWidht();
        height = gui.getGUIHeight();
    }

    public void restartLife(int cytesCount) {
        cytes.clear();
        for (int i = 0; i < cytesCount; i++) {
            cytes.add(new Cyte(this));
        }
    }

    @Override
    public float getBestProliferateCoefficient() {
        return bestProliferateCoefficient;
    }

    @Override
    public float getBestSurvivalCoefficient() {
        return bestSurvivalCoefficient;
    }

    @Override
    public float getBestInvasivenessCoefficient() {
        return bestInvasivenessCoefficient;
    }

    @Override
    public float getBestMobilityCoefficient() {
        return bestMobilityCoefficient;
    }

    @Override
    public int getUniverseWidth() {
        return width;
    }

    @Override
    public int getUniverseHeight() {
        return height;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            updateSize();
            float time = timeSpeed * (1f / (float) ups);
            this.time += time;
            updateUniverseConditionals();
            for (int i = 0; i < cytes.size(); i++) {
                Cyte cyte = cytes.get(i);
                if (cyte != null) {
                    ActionCode c = cyte.update(time);
                    if (c != null) {
                        if (c.getAction() == ActionCode.ACTION_DEATH) {
                            killCyte(cyte);
                        } else if (c.getAction() == ActionCode.ACTION_PROLIFERATE) {
                            proliferateCyte(cyte);
                        }
                    }
                }
            }
            Random r = new Random();
            while (cytes.size() > maxCytesCount) {
                killCyte(cytes.get(r.nextInt(cytes.size())));
            }
            gui.drawCytes(cytes);
            gui.showCount(cytes.size());
            gui.showInvasiveness(bestInvasivenessCoefficient);
            gui.showMove(bestMobilityCoefficient);
            gui.showProliferate(bestProliferateCoefficient);
            gui.showSurvival(bestSurvivalCoefficient);
            gui.showTimeSpeed(timeSpeed);
            try {
                Thread.sleep(1000 / ups);
            } catch (InterruptedException ex) {
                Logger.getLogger(TheUniverse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected void updateUniverseConditionals() {
        bestInvasivenessCoefficient = (float) (Math.cos(time * Math.PI * 2f * 0.001) * 0.5f + 0.5f);
        bestMobilityCoefficient = (float) (Math.cos(time * Math.PI * 2f * 0.0002) * 0.5f + 0.5f);
        bestProliferateCoefficient = (float) (Math.cos(time * Math.PI * 2f * 0.00015) * 0.5f + 0.5f);
        bestSurvivalCoefficient = (float) (Math.cos(time * Math.PI * 2f * 0.0001) * 0.5f + 0.5f);
    }

    protected void proliferateCyte(Cyte c) {
        Cyte newCyte = new Cyte(this, mutagenicCoefficient,
                c.getProliferateCoefficient(),
                c.getSurvivalCoefficient(),
                c.getInvasivenessCoefficient(),
                c.getMobilityCoefficient(),
                c.getPositionX(), c.getPositionY());
        cytes.add(newCyte);
    }

    protected void killCyte(Cyte c) {
        cytes.remove(c);
    }

    @Override
    public float getMaximalMoovingSpeed() {
        return maximalMoovingSpeed;
    }

    @Override
    public void restartUniverse(int cytesCount) {
        restartLife(cytesCount);
    }

    @Override
    public void setTimeSpeed(float timeSpeed) {
        this.timeSpeed = timeSpeed;
    }
}
