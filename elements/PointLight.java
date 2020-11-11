package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * Class that is representing a point light
 * @author Shoshana Chaya and Yael
 */
public class PointLight extends Light implements LightSource {
    /************ fields ****************/
    protected Point3D _position;
    protected double _kC;
    protected double _kL;
    protected double _kQ;

    /**
     * constructor that gets a color, point3D, kc, kl, kd
     * @param colorIntensity color value
     * @param _position point3D value
     * @param _kC reduction factor
     * @param _kL reduction factor
     * @param _kQ reduction factor
     */
    public PointLight(Color colorIntensity, Point3D _position, double _kC, double _kL, double _kQ) {
        super(colorIntensity);
        this._position = new Point3D(_position);
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }

/******************* getters ******************/
    /**
     * returns the intensity in a given point
     * @param p the lighted point
     * @return color value in the given point
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        Color IL = _intensity.reduce(_kC + d*_kL + dsquared * _kQ);
        return IL;
    }

    /**
     * return the vector direction to a point
     * @param p the lighted point
     * @return direction
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        } else {
            return p.subtract(_position).normalize();
        }
    }

    /**
     * returns the distance between the given point to the light source
     * @param point point3D value
     * @return distance
     */
    @Override
    public double getDistance(Point3D point) {
       return point.distance(_position);
    }
}