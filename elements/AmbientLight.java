package elements;

import primitives.Color;
/**
 * Class AmbientLight is the basic class representing an AmbientLight
 * @author Shoshana Chaya and Yael
 */
public class AmbientLight extends  Light{

    /**
     * Constructor that gets a color and a double value
     * @param _intensity color value
     * @param ka double value
     */
    public AmbientLight(Color _intensity, double ka) {
        super(_intensity.scale(ka));
    }

}