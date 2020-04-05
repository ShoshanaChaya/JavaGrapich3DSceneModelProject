package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class Cylinder is the basic class representing a cylinder
 * @author Shoshana Chaya and Yael
 */
public class Cylinder extends RadialGeometry implements Geometry
{
        /*************** fields ****************/
        double height;
/******************** constructors ******************/
/**
 * constuctor that gets a radius and height
 * @param _radius
 * @param height
 */
public Cylinder(double _radius, double height) {
        super(_radius);
        this.height = height;
        }
/******************** getter ***********************/
public double getHeight() {
        return height;
        }

@Override
public Vector getNormal(Point3D point) {
        Vector v = new Vector(point);
        Point3D p = new Point3D(point);
        double t = v.dotProduct(new Vector(p));
        Point3D o = new Point3D(v.multiple(t).get_point());
        Vector n = (p.subtract(o)).normalize();
        return n;
        }


@Override
public String toString() {
        return  "height=" + height +
        ", _radius=" + _radius;
        }
}
