package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;


/**
 * Class Sphere is the basic class representing a sphere
 * @author Shoshana Chaya and Yael
 */
public class Sphere extends RadialGeometry {
    /************** field ********************/
    Point3D point;

    /************************ constructor ***********************/
    /**
     * constructor that gets a color, material, radius, point
     *
     * @param emissionLight color value
     * @param material      material value
     * @param radius        radius
     * @param center        center point
     */
    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, radius);
        this._material = material;
        this.point = new Point3D(center);
    }

    /**
     * constructor that gets a color, radius, point
     *
     * @param emissionLight color value
     * @param radius        radius
     * @param center        center point
     */
    public Sphere(Color emissionLight, double radius, Point3D center) {
        this(emissionLight, new Material(0, 0, 0), radius, center);
    }

    /**
     * constructor that gets a radius and point
     * @param radius radius
     * @param center center point
     */
    public Sphere(double radius, Point3D center) {
        this(Color.BLACK, new Material(0, 0, 0), radius, center);
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
     * @param point point that from it starts the normal
     * @return normal
     */
    @Override
    public Vector getNormal(Point3D point) {
        return (point.subtract(this.point).normalize());
    }

    /**
     * finds intersections of the sphere
     * @param ray ray which does the intersections
     * @return a list of intersection points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        // saved fields for the function
        Point3D p0 = ray.get_point();
        Vector v = ray.get_vector();
        Vector u;
        try { // tries to calculate the vector between the camera and the intersection point
            u = point.subtract(p0);   // p0 == _center
        }
        catch (IllegalArgumentException e) { // if failed
            return List.of(new GeoPoint(this, (ray.getTargetPoint(this._radius))));
        }

        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this._radius * this._radius - dSquared);

        if (thSquared <= 0) { // there aren't any intersection points
            return null;
        }

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) {// there aren't any intersection points
            return null;
        }

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        double t1dist = alignZero(maxDistance - t1);
        double t2dist = alignZero(maxDistance - t2);

        if (t1 <= 0 && t2 <= 0) { // there aren't any intersection points
            return null;
        }

        // adds the amount of intersection points depending on which scenario
        if (t1 > 0 && t2 > 0) {
            if (t1dist > 0 && t2dist > 0) {
                return List.of( new GeoPoint(this, (ray.getTargetPoint(t1))), new GeoPoint(this, (ray.getTargetPoint(t2)))); //P1 , P2
            }
            else if (t1dist > 0) {
                return List.of(new GeoPoint(this, (ray.getTargetPoint(t1))));
            }
            else if (t2dist > 0) {
                return List.of(new GeoPoint(this, (ray.getTargetPoint(t2))));
            }
        }

        if ((t1 > 0) && (t1dist > 0)) {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t1))));
        }
        else if ((t2 > 0) && (t2dist > 0)) {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t2))));
        }
        return null; // there aren't any intersection points
    }
}
