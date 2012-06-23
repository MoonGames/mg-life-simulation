/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources.model;

import java.util.Random;

/**
 *
 * @author indy
 */
public class Cyte {

    protected UniverseInterface universe = null;
    protected float proliferateCoefficient = 0.5f;
    protected float survivalCoefficient = 0.5f;
    protected float invasivenessCoefficient = 0.5f;
    protected float mobilityCoefficient = 0.5f;
    protected float positionX = 0;
    protected float positionY = 0;

    /**
     * vytvori bunku s nahodnymi vlastnostmi
     */
    public Cyte(UniverseInterface universe) {
        this.universe = universe;
        Random r = new Random(System.nanoTime());
        proliferateCoefficient = r.nextFloat();
        survivalCoefficient = r.nextFloat();
        invasivenessCoefficient = r.nextFloat();
        mobilityCoefficient = r.nextFloat();
        positionX = r.nextInt(universe.getUniverseWidth());
        positionY = r.nextInt(universe.getUniverseHeight());
    }

    /**
     *
     * @param mutagenicCoefficient velikost maximalni mutace
     * @param proliferateCoefficient
     * @param survivalCoefficient
     * @param invasivenessCoefficient
     */
    public Cyte(UniverseInterface universe, float mutagenicCoefficient, float proliferateCoefficient, float survivalCoefficient, float invasivenessCoefficient, float mobilityCoefficient, int positionX, int positionY) {
        this.universe = universe;
        Random r = new Random(System.nanoTime());
        this.proliferateCoefficient = (r.nextFloat() * 2 - 1) + proliferateCoefficient;
        this.survivalCoefficient = (r.nextFloat() * 2 - 1) + survivalCoefficient;
        this.invasivenessCoefficient = (r.nextFloat() * 2 - 1) + invasivenessCoefficient;
        this.mobilityCoefficient = (r.nextFloat() * 2 - 1) + mobilityCoefficient;
        normalizeCoefficient();
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * pokud nejaky koefficient pretekl mimo interval <0, 1> postupnym
     * pricitanim 1 nebo -1 ho posune do tohoto intervalu
     */
    protected void normalizeCoefficient() {
        proliferateCoefficient = getNormalizeValue(proliferateCoefficient);
        survivalCoefficient = getNormalizeValue(survivalCoefficient);
        invasivenessCoefficient = getNormalizeValue(invasivenessCoefficient);
        mobilityCoefficient = getNormalizeValue(mobilityCoefficient);
    }

    protected float getNormalizeValue(float value) {
        while (value < 0) {
            value += 1;
        }
        while (value > 1) {
            value -= 1;
        }
        return value;
    }

    public ActionCode update(float time) {
        updateMove(time);
        ActionCode ac = null;
        ac = updateDeath(time);
        if (ac == null) {
            ac = updateProliferate(time);
        }
        return ac;
    }

    protected ActionCode updateProliferate(float time) {
        Random r = new Random();
        float a1 = 0.02f * time * (float) Math.pow(Math.abs(proliferateCoefficient - universe.getBestProliferateCoefficient()) - 1f, 2f);
        if (r.nextFloat() < a1) {
            return new ActionCode(ActionCode.ACTION_PROLIFERATE);
        }
        return null;
    }

    protected ActionCode updateDeath(float time) {
        Random r = new Random();
        float a1 = time * 0.05f * Math.abs(survivalCoefficient - universe.getBestSurvivalCoefficient());
        if (r.nextFloat() < a1) {
            return new ActionCode(ActionCode.ACTION_DEATH);
        }
        return null;
    }

    protected void updateMove(float time) {
        Random r = new Random();
        float size = time * universe.getMaximalMoovingSpeed() * (float) Math.pow(Math.abs(universe.getBestMobilityCoefficient() - mobilityCoefficient) - 1f, 2f);
        float angle = (float) (Math.PI * 2f * r.nextFloat());
        float newX = (float) (positionX + Math.cos(angle) * size);
        while (newX < 0) {
            newX += universe.getUniverseWidth();
        }
        while (newX > universe.getUniverseWidth()) {
            newX -= universe.getUniverseWidth();
        }
        positionX = newX;
        float newY = (float) (positionY + Math.sin(angle) * size);
        while (newY < 0) {
            newY += universe.getUniverseHeight();
        }
        while (newY > universe.getUniverseHeight()) {
            newY -= universe.getUniverseHeight();
        }
        positionY = newY;
    }

    public float getInvasivenessCoefficient() {
        return invasivenessCoefficient;
    }

    public float getMobilityCoefficient() {
        return mobilityCoefficient;
    }

    public int getPositionX() {
        return (int) positionX;
    }

    public int getPositionY() {
        return (int) positionY;
    }

    public float getProliferateCoefficient() {
        return proliferateCoefficient;
    }

    public float getSurvivalCoefficient() {
        return survivalCoefficient;
    }
}
