/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sources.model;

/**
 *
 * @author indy
 */
public interface UniverseInterface {

    public float getBestProliferateCoefficient();

    public float getBestSurvivalCoefficient();

    public float getBestInvasivenessCoefficient();

    public float getBestMobilityCoefficient();
    
    public int getUniverseWidth();
    
    public int getUniverseHeight();
    
    public float getMaximalMoovingSpeed();
}
