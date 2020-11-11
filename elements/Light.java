package elements;

import primitives.Color;
/**
 * Class tht is representing a light
 * @author Shoshana Chaya and Yael
 */
public abstract class Light {
    /**
     * _intensity value, set to protected
     */
    protected Color _intensity;

    /**
     * constructor that gets a color
     * @param _intensity color value
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

/******************* getter *****************/
    public Color get_intensity() {
        return _intensity;
    }
}