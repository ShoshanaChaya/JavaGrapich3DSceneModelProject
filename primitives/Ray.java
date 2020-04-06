package primitives;

/**
 * Class Ray is the basic class representing a vector with a starting point
 * @author Shoshana Chaya and Yael
 */
public class Ray
{
    /************* fields ************/
    private final Point3D _point;
    private final Vector _vector;
/************************* constructors ********************/
    /**
     * constructor that gets vector and a point
     * @param _point point3D value
     * @param _vector vector value
     */
    public Ray(Point3D _point, Vector _vector) {
        if(_vector.length() != 1) {
            if (1 - _vector.length() != 1.1102230246251565E-16) {
                throw new IllegalArgumentException("Vector must be normalized");
            }
        }
        this._point = _point;
        this._vector = _vector;
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
        if (Util.isZero(t)){
            return _point;
        }
        return new Point3D(_point).add(_vector.scale(t));
    }
}
