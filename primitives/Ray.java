package primitives;

import java.util.Random;

import static primitives.Util.isZero;

/**
 * Class Ray is the basic class representing a vector with a starting point
 * @author Shoshana Chaya and Yael
 */
public class Ray
{
    /************* fields ************/
    private final Point3D _point;
    private final Vector _vector;
    private static final double DELTA = 0.1;
    private static final Random rnd = new Random();
/************************* constructors ********************/
    /**
     * constructor that gets vector and a point
     * @param _point point3D value
     * @param _vector vector value
     */
    public Ray(Point3D _point, Vector _vector) {
        this._point = _point;
        this._vector = _vector.normalize();
    }

    /**
     * constructor that gets a point, direction, normal
     * @param point point3D value
     * @param direction vector value
     * @param normal vector value
     */
    public Ray(Point3D point, Vector direction, Vector normal) {
        _vector = new Vector(direction).normalize();

        double nv = normal.dotProduct(direction);

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _point = point.add(normalDelta);
    }

    /**
     * copy constructor
     * @param ray ray value
     */
    public Ray(Ray ray){
        if (ray._vector.length() != 1){
            throw new IllegalArgumentException("Vector must be normalized");
        }
        this._point = ray._point;
        this._vector = ray._vector;
    }
/******************* getter *******************/
    public Point3D get_point() {
        return new Point3D(_point);
    }

    public Vector get_vector() {
        return new Vector(_vector);
    }

    @Override
    public String toString() {
        return "_point=" + _point +
                ", _vector=" + _vector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _point.equals(ray._point) &&
                _vector.equals(ray._vector);
    }

    /**
     * returns intersection point
     * @param t double value
     * @return Point point3D value
     */
    public Point3D getTargetPoint(double t) {
        if (isZero(t)){
            return _point;
        }
        return new Point3D(_point).add(_vector.scale(t));
    }
}
