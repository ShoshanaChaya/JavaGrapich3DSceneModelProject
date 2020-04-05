package primitives;

/**
 * Class Ray is the basic class representing a vector with a starting point
 * @author Shoshana Chaya and Yael
 */
public class Ray
{
    /************* fields ************/
    Point3D _point;
    Vector _vector;
/************************* constructors ********************/
    /**
     * constructor that gets vector and a point
     * @param _point
     * @param _vector
     */
    public Ray(Point3D _point, Vector _vector) {
        if (_vector.length() != 1){
            throw new IllegalArgumentException("Vector must be normalized");
        }
        this._point = _point;
        this._vector = _vector;
    }

    /**
     * copy constructor
     * @param ray
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
        return _point;
    }

    public Vector get_vector() {
        return _vector;
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
}
