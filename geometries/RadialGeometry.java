package geometries;

/**
 * Class RadialGeometry is the basic class representing a RadialGeometry
 * @author Shoshana Chaya and Yael
 */
public abstract class RadialGeometry
{
    /****************** field *********************/
    public double _radius;

/****************** constructors ********************/
    /**
     * constructor that gets a double value
     * @param _radius
     */
    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

/**************** getter *******************/
    public double get_radius() {
        return _radius;
    }
}
