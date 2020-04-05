package geometries;

import primitives.Point3D;
import primitives.Vector;

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
     * @param _radius
     * @param point
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

    @Override
    public Vector getNormal(Point3D point) {
        return (point.subtract(this.point).normalize());
    }
}
