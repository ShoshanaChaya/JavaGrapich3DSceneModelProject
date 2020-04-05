package primitives;

/**
 * Class Point3D is the basic class representing a point
 * 3-Dimensional coordinate system.
 * @author Shoshana Chaya and Yael
 */

public class Point3D
{
    /************* fields ************/
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    public static final Point3D ZERO= new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0));

/********************* constructors ********************/
    /**
     * parameter constructor
     * @param _x Coordinate value
     * @param _y Coordinate value
     * @param _z Coordinate value
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }

    /**
     * parameter constructor
     * @param _x double value
     * @param _y double value
     * @param _z double value
     */
    public Point3D(double _x, double _y, double _z) {
        this._x = new Coordinate(_x);
        this._y = new Coordinate(_y);
        this._z = new Coordinate(_z);
    }

    /**
     * copy constructor
     * @param _point
     */
    public Point3D(Point3D _point)
    {
        this._x = _point._x;
        this._y = _point._y;
        this._z = _point._z;
    }

    /********************* getter ************************/
    public Coordinate get_x() {
        return _x;
    }

    public Coordinate get_y() {
        return _y;
    }

    public Coordinate get_z() {
        return _z;
    }

    public Point3D get()
    {
        Point3D point = new Point3D(_x, _y, _z);
        return point;
    }

    /******************* math functions *******************/
    /**
     * substract one point from the other
     * @param _point
     * @return vector
     */
    public Vector subtract (Point3D _point)
    {
        Vector point = new Vector(new Point3D(_x.subtract(_point._x), _y.subtract(_point._y), _z.subtract(_point._z)));
        return point;
    }

    /**
     * adds one point to another
     * @param _vector
     * @return Point3D
     */
    public Point3D add (Vector _vector)
    {
        Point3D point = new Point3D(_x.add( _vector.get_point().get_x()), _y.add( _vector.get_point().get_y()), _z.add( _vector.get_point().get_z()));
        return point;
    }
    /**
     * divides one point in another
     * @param _vector
     * @return Point3D
     */
    public Point3D divide (double _vector)
    {
        Point3D point = new Point3D(_x.divide( _vector), _y.divide( _vector), _z.divide( _vector));
        return point;
    }

    public Point3D multiple (double t){
        Coordinate c = new Coordinate(t);
        Point3D point = new Point3D(_x.multiple(c), _y.multiple(c), _z.multiple(c));
        return point;
    }
    /**
     * calculates distance between two points multiplied by itself
     * @param _point
     * @return distance in double value
     */
    public double distanceSquared (Point3D _point){
        Coordinate x = _x.subtract(_point._x).multiple(_x.subtract(_point._x));
        Coordinate y = _y.subtract(_point._y).multiple(_y.subtract(_point._y));
        Coordinate z = _z.subtract(_point._z).multiple(_z.subtract(_point._z));
        return x.add(y.add(z));
        //return _x.subtract(_point._x).multiple(_x.subtract(_point._x)).add(_y.subtract(_point._y).multiple(_y.subtract(_point._y)));
    }
    /**
     * calculates distance between two points
     * @param _point
     * @return distance in double value
     */
    public double distance (Point3D _point)
    {
        return Math.sqrt(distanceSquared(_point));
    }

    @Override
    public String toString() {
        return "(" + _x +
                ", " + _y +
                ", " + _z +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }
}
