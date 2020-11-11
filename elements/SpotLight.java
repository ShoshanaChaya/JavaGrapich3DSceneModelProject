package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * Class that is representing a point light
 * @author Shoshana Chaya and Yael
 */
public class SpotLight extends PointLight{
    /************** fields ****************/
    private Vector _direction;

    /**
     * constructor that gets a color, point3D, vector, kc, kl, kq
     * @param colorIntensity color value
     * @param _position the position of the light source
     * @param _direction the direction of the light source
     * @param _kC reduction factor
     * @param _kL reduction factor
     * @param _kQ reduction factor
     */
    public SpotLight(Color colorIntensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
        super(colorIntensity,_position,_kC,_kL,_kQ);
        this._direction = new Vector(_direction).normalized();
    }

    /**************** getters *******************/
    /**
     * returns the intensity in a given point
     * @param p the lighted point
     * @return color value in the given point
     */
    @Override
    public Color getIntensity(Point3D p) {
        Color pointLightIntensity = super.getIntensity(p);
        double projection = _direction.dotProduct(getL(p));
        Color IL = pointLightIntensity.scale(Math.max(0,projection));
        return IL;
    }
    /**
     * return the vector direction to a point
     * @param p the lighted point
     * @return direction
     */
    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }

    /**
     * returns the distance between the given point to the light source
     * @param point point3D value
     * @return distance
     */
    @Override
    public double getDistance(Point3D point) {
        return super.getDistance(point);
    }
}