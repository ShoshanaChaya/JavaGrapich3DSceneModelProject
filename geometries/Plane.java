package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Class Plane is the basic class representing a plane
 * @author Shoshana Chaya and Yael
 */
public class Plane implements Geometry
{
    /**************** fields *********************/
    Point3D point;
    Vector normal;
    /************************ constructors ********************/
    /**
     * constructor that gets 3 points
     * @param firstPoint point3D value
     * @param secondPoint point3D value
     * @param thirdPoint point3D value
     */
    public Plane(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        this.point = firstPoint;
        Vector vec = new Vector(firstPoint.subtract(secondPoint));
        Vector vec2 = new Vector(firstPoint.subtract(thirdPoint));
        Vector vec3 = new Vector(vec.crossProduct(vec2));
        this.normal = (vec3.normalize().scale(-1));
    }

    /**
     * constructor that gets a point and normal
     * @param firstPoint point3D value
     * @param normal vector value
     */
    public Plane(Point3D firstPoint, Vector normal)
    {
        this.point = firstPoint;
        this.normal = (normal.normalize().scale(-1));
    }
    /**************** getter ********************/
    public Point3D getPoint() {
        return point;
    }

    /**
     * calculates the normal to a given point
     * @param point point3D value
     * @return vector vector value
     */
    @Override
    public Vector getNormal(Point3D point) {
        return normal.scale(-1);
    }

    /**
     * calculates the normal to a given point
     * @return vector vector value
     */
    public Vector getNormal() {
        return normal.scale(-1);
    }

    @Override
    public String toString() {
        return  "point=" + point +
                ", normal=" + normal;
    }

    /**
     * finds intersections of the plane
     * @param ray ray value
     * @return a list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = point.subtract(ray.get_point());
        }
        catch (IllegalArgumentException e) {
            return null; // if the ray starts from point Q - there are no intersections
        }

        double nv = normal.dotProduct(ray.get_vector());
        if (Util.isZero(nv)) // if the ray is parallel to the plane - there are no intersections
            return null;

        double t = Util.alignZero(normal.dotProduct(p0Q) / nv);

        if (t<=0){
            return null;
        }
        return List.of(ray.getTargetPoint(t));
    }
}
