package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * Class that is representing a directional light
 * @author Shoshana Chaya and Yael
 */
public class DirectionalLight extends Light implements LightSource {
    /************fields ******************/
    private Vector _direction;

    /**
     * Initialize directional light with it's intensity and direction, direction vector will be normalized.
     * @param colorintensity intensity of the light
     * @param direction      direction vector
     */

    public DirectionalLight(Color colorintensity, Vector direction) {
        super(colorintensity);
        _direction = direction.normalized();
    }

    /********************************* getters ***********************************/
    /**
     * @param p the lighted point is not used and is mentioned only for compatibility with LightSource
     * @return fixed intensity of the directionLight
     */
    @Override
    public Color getIntensity(Point3D p) {
        return this._intensity;
    }

    //instead of getDirection()

    /**
     * returns the directions
     * @param p the lighted point
     * @return direction
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    /**
     * returns the distance between the given point to the light source
     * @param point point3D value
     * @return distance
     */
    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}