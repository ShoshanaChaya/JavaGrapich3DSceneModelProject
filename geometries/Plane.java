package geometries;

import primitives.Point3D;
import primitives.Vector;

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
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     */
    public Plane(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        this.point = firstPoint;
        Vector vec = new Vector(firstPoint.subtract(secondPoint));
        Vector vec2 = new Vector(firstPoint.subtract(thirdPoint));
        Vector vec3 = new Vector(vec.crossProduct(vec2));
        this.normal = (vec3.normalize().scale(-1));
    }

    /**
     * constructor that gets a point ang normal
     * @param firstPoint
     * @param normal
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
     * @return vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        return normal.scale(-1);
    }

    public Vector getNormal() {
        return normal.scale(-1);
    }

    @Override
    public String toString() {
        return  "point=" + point +
                ", normal=" + normal;
    }
}
