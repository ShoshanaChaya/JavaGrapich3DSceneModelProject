package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
     * @param _radius double value
     * @param ray ray value
     */
    public Tube(double _radius, Ray ray) {
        super(_radius);
        this.ray = ray;
    }

    /**
     * constructor that gets a double value
     * @param radius double value
     */
    public Tube(double radius) {
        super(radius);
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
     * @param point point3D value
     * @return vector vector value
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = ray.get_point();
        Vector v = ray.get_vector();

        Vector vector1 = point.subtract(o);

        double projection = vector1.dotProduct(v);
        if(projection == 0)
        {
            // projection of P-O on the ray:
            o.add(v.scale(projection));
        }

        Vector check = point.subtract(o);
        return check.normalize();
    }

    /**
     * finds intersections of the tube
     * @param ray ray value
     * @return a list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
