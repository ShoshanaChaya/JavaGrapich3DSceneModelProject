package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;


/**
 * Class Sphere is the basic class representing a sphere
 * @author Shoshana Chaya and Yael
 */
public class Sphere extends RadialGeometry implements Geometry
{
    /************** field ********************/
    Point3D point;
/************************ constructor ***********************/
    /**
     * constror that gets a radius and point
     * @param _radius double value
     * @param point point3D value
     */
    public Sphere(double _radius, Point3D point) {
        super(_radius);
        this.point = point;
    }

/************ getter ****************/
    public Point3D getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "point=" + point +
                ", _radius=" + _radius;
    }

    /**
     * calculates the normal to a given point
     * @param point point3D value
     * @return vector vector value
     */
    @Override
    public Vector getNormal(Point3D point) {
        return (point.subtract(this.point).normalize());
    }

    /**
     * finds intersections of the sphere
     * @param ray ray value
     * @return a list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.get_point();
        Vector v = ray.get_vector();
        Vector u;
        try {
            u = point.subtract(p0);
        } catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared;
        if (tm == 0){
            dSquared = u.lengthSquared();
        }
        else{
            dSquared = u.lengthSquared() - tm*tm;
        }
        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0){
            return null;
        }
        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0){
            return null;
        }
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0){
            return null;
        }
        if (t1 > 0 && t2 > 0){
            return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2));
        }
        if (t1 > 0) {
            return List.of(ray.getTargetPoint(t1));
        }
        else {
            return List.of(ray.getTargetPoint(t2));
        }
    }
}
