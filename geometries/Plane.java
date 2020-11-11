package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class Plane is the basic class representing a plane
 * @author Shoshana Chaya and Yael
 */
public class Plane extends FlatGeometry
{
    /**************** fields *********************/
    Point3D point;
    Vector normal;
    /************************ constructors ********************/
    /**
     * constructor that gets 3 points
     * @param emissionLight color value
     * @param material material value
     * @param firstPoint point3D of plane
     * @param secondPoint point3D of plane
     * @param thirdPoint point3D of plane
     */
    public Plane(Color emissionLight, Material material, Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        super(emissionLight, material);
        this.point = firstPoint;
        Vector vec = new Vector(firstPoint.subtract(secondPoint));
        Vector vec2 = new Vector(firstPoint.subtract(thirdPoint));
        Vector vec3 = new Vector(vec.crossProduct(vec2)); // calculates the third vector by doing cross product to the first 2 vectors
        this.normal = (vec3.normalize().scale(-1));
    }

    /**
     * constructor that gets 3 points
     * @param firstPoint point3D of plane
     * @param secondPoint point3D of plane
     * @param thirdPoint point3D of plane
     */
    public Plane(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint) {
        this(Color.BLACK, firstPoint, secondPoint, thirdPoint);
    }

    /**
     * constructor that gets a color, material, point, vector
     * @param emission color value
     * @param material material value
     * @param _point point3D of plane
     * @param _normal normal of the plane
     */
    public Plane(Color emission, Material material, Point3D _point, Vector _normal) {
        super(emission, material);
        this.point = _point;
        this.normal = _normal.normalize();
    }

    /**
     * constructor that gets a color, point, vector
     * @param emission color value
     * @param _point point3D of plane
     * @param _normal normal of the plane
     */
    public Plane(Color emission, Point3D _point, Vector _normal) {
        this(emission, new Material(0,0,0), _point, _normal);
    }

    /**
     * constructor that gets a point and normal
     * @param firstPoint point3D of plane
     * @param normal normal of the plane
     */
    public Plane(Point3D firstPoint, Vector normal)
    {
        super(Color.BLACK, new Material(0, 0, 0));
        this.point = firstPoint;
        this.normal = (normal.normalize().scale(1));
    }

    /**
     * constructor that gets a color and 3 points
     * @param emissionLight color values
     * @param p1 point3D of plane
     * @param p2 point3D of plane
     * @param p3 point3D of plane
     */
    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
    }
    /**************** getter ********************/
    public Point3D getPoint() {
        return point;
    }

    /**
     * calculates the normal to a given point
     * @param point point that from it starts the normal
     * @return vector normal
     */
    @Override
    public Vector getNormal(Point3D point) {
        return normal.scale(-1);
    }

    /**
     * calculates the normal to a given point
     * @return normal
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
     * @param ray ray which does the intersections
     * @param maxDistance the maximum distance
     * @return a list of intersection points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        Vector p0Q;
        // if the ray starts from point Q - there are no intersections
        try {
            p0Q = point.subtract(ray.get_point());
        }
        catch (IllegalArgumentException e) {
            return null;
        }

        double nv = normal.dotProduct(ray.get_vector());
        if (Util.isZero(nv)) // if the ray is parallel to the plane - there are no intersections
            return null;

        double t = alignZero(normal.dotProduct(p0Q) / nv);
        double tdist = alignZero(maxDistance - t);

        if (t <= 0|| (tdist <= 0)){ // there aren't any intersection points
            return null;
        }
        GeoPoint geo = new GeoPoint(this, ray.getTargetPoint(t));
        return List.of(geo);
    }
}
