package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Class Triangle is the basic class representing a triangle
 * @author Shoshana Chaya and Yael
 */
public class Triangle extends Polygon
{
    /*********************** constructor ********************/
    /**
     * constuctor that gets 3 points
     * @param firstPoint point3D value
     * @param secondPoint point3D value
     * @param thirdPoint point3D value
     */
    public Triangle(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint)
    {
        super(firstPoint, secondPoint, thirdPoint);
    }

    /**
     * calculates the normal to a given point
     * @param point point3D value
     * @return vector vector value
     */
    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return  "_vertices=" + _vertices +
                ", _plane=" + _plane;
    }

    /**
     * finds intersections of the triangle
     * @param ray ray value
     * @return a list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) {
            return null;
        }

        Point3D p0 = ray.get_point();
        Vector v = ray.get_vector();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (Util.isZero(s1)) {
            return null;
        }
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (Util.isZero(s2)) {
            return null;
        }
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (Util.isZero(s3)) {
            return null;
        }

        if((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)){
            return intersections;
        }
        return null;

    }
}
