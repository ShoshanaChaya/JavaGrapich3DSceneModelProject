package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class Cylinder is the basic class representing a cylinder
 * @author Shoshana Chaya and Yael
 */
public class Cylinder extends Tube implements Geometry
{
        /*************** fields ****************/
        double height;
/******************** constructors ******************/
/**
 * constuctor that gets a radius and height
 * @param _radius double value
 * @param _ray ray value
 * @param _height double value
 */
public Cylinder(double _radius, Ray _ray, double _height) {
        super(_radius, _ray);
        this.height = _height;
}

/**
 * copy constructor
 * @param other cylinder value
 */
public Cylinder(Cylinder other) {
        super(other._radius, other.ray);
        this.height = other.height;
}

/******************** getter ***********************/
public double getHeight() {
        return height;
        }

/**
* returns the normal of th cylinder
* @param point point3d value
* @return vector vector value
**/
@Override
public Vector getNormal(Point3D point)
{
        Ray r =new Ray(getRay());
        Vector v = new Vector(ray.get_vector());
        Point3D o = new Point3D(ray.get_point());

        double t;
        try {
                t = alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) {
                return v;
        }

        if (t == 0 || isZero(height - t))
                return v;

        o = o.add(v.scale(t));
        return point.subtract(o).normalize();

        }


@Override
public String toString() {
        return  "height=" + height +
        ", _radius=" + _radius;
        }

/**@Override
public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
        }**/
}
