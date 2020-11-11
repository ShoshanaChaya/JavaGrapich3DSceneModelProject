package geometries;

import primitives.Color;

import static primitives.Util.isZero;

/**
 * Class RadialGeometry is the basic class representing a RadialGeometry
 * @author Shoshana Chaya and Yael
 */
public abstract class RadialGeometry extends Geometry
{
    /****************** field *********************/
    public double _radius;

/****************** constructors ********************/

    /**
     *constructor that gets a color and radius
     * @param _radius radius
     */
    public RadialGeometry(Color emissionLight, double _radius) {
        super(emissionLight);
        if (isZero(_radius) || (_radius < 0.0))
            throw new IllegalArgumentException("radius "+ _radius +" is not valid");
        this._radius = _radius;
    }

/**************** getter *******************/
    public double get_radius() {
        return _radius;
    }
}
