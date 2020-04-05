package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Tube is the basic class representing a tube
 * @author Shoshana Chaya and Yael
 */
public class Tube extends RadialGeometry implements Geometry
{
    /************* field ****************/
    Ray ray;

/************* constructor ***********/
    /**
     * constructor that gets a radius and ray
     * @param _radius
     * @param ray
     */
    public Tube(double _radius, Ray ray) {
        super(_radius);
        this.ray = ray;
    }
/************ getter ******************/
    public Ray getRay() {
        return ray;
    }

    @Override
    public String toString() {
        return  "ray=" + ray +
                ", _radius=" + _radius;
    }
    /**
     * calculates the normal to a given point
     * @return vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector v = new Vector(point);
        Point3D p = new Point3D(point);
        double t = v.dotProduct(new Vector(p));
        Point3D o = new Point3D(v.multiple(t).get_point());
        Vector n = (p.subtract(o)).normalize();
        return n;
    }
}
