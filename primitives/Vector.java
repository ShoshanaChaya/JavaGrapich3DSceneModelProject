package primitives;

/**
 * Class Vector is the basic class representing a vector
 * @author Shoshana Chaya and Yael
 */

public class Vector
{
    /************* fields ************/
    private Point3D _point;
/****************** constructors *******************/
    /**
     * constructor that gets 3 coordinates
     * @param _x
     * @param _y
     * @param _z
     */
    public Vector(Coordinate _x, Coordinate _y, Coordinate _z) {
        Point3D point=new Point3D(new Coordinate(_x), new Coordinate(_y), new Coordinate(_z));
       if (point.equals(Point3D.ZERO))
       {
           throw new IllegalArgumentException("The point can't be zero");
       }
        _point=point;
    }

    /**
     * constructor that gets 2 points
     * @param p1
     * @param p2
     */
    public Vector(Point3D p1, Point3D p2) {
        this(p1.subtract(p2));
    }

    /**
     * constructor that gets 3 double parameters
     * @param _x
     * @param _y
     * @param _z
     */
    public Vector(double _x, double _y, double _z) {
        Point3D point=new Point3D(new Coordinate(_x), new Coordinate(_y), new Coordinate(_z));
        if (point.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("The point can't be zero");
        }
        _point=point;
    }

    /**
     * constructor that gets a point
     * @param _point
     */
    public Vector(Point3D _point) {
        if (_point.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("The point can't be zero");
        }
        this._point = _point;
    }

    /**
     * copy constructor
     * @param _vector
     */
    public Vector (Vector _vector)
    {
        _point=_vector._point;
    }

/************************* getter *************************/

    /**
     * Vector value getter
     *
     * @return vector value
     */public Point3D get_point() {
        return _point;
    }

    /****************** math functions *********************/
    /**
     * adds one vector to another
     * @param vec
     * @return vector
     */
    public Vector add(Vector vec)
    {
        Vector vector = new Vector( _point.add(vec));
        return vector;
    }

    /**
     * subtracts one vector from another
     * @param vec
     * @return vector
     */
    public Vector subtract (Vector vec)
    {
        Vector vector = new Vector( _point.subtract(vec._point));
        return vector;
    }

    public Vector multiple(double t){
        Vector vector = new Vector(_point.multiple(t));
        return vector;
    }

    /**
     * multiplies a vector with a number
     * @param num
     * @return vector
     */
    public Vector scale (double num)
    {
        Coordinate cord = new Coordinate(num);
        Vector vec = new Vector(_point.get_x().multiple(cord),_point.get_y().multiple(cord),_point.get_z().multiple(cord));
        return vec;
    }

    /**
     * multiplies a vector with another by number
     * @param vec
     * @return double value
     */
    public double dotProduct (Vector vec)
    {
        Coordinate x = _point.get_x().multiple(vec._point.get_x());
        Coordinate y = _point.get_y().multiple(vec._point.get_y());
        Coordinate z = _point.get_z().multiple(vec._point.get_z());
        return x.add(y.add(z));
    }

    /**
     * multiplies a vector with another
     * @param vec
     * @return vector
     */
    public Vector crossProduct (Vector vec)
    {
        Coordinate x = new Coordinate(_point.get_y().multiple(vec._point.get_z()).subtract(_point.get_z().multiple(vec._point.get_y())));
        Coordinate y = new Coordinate(_point.get_z().multiple(vec._point.get_x()).subtract(_point.get_x().multiple(vec._point.get_z())));
        Coordinate z = new Coordinate(_point.get_x().multiple(vec._point.get_y()).subtract(_point.get_y().multiple(vec._point.get_x())));
        return new Vector(x, y, z);
    }

    /**
     * calculates a vectors length multiplied by itself
     * @return double value
     */
    public double lengthSquared ()
    {
        Coordinate x = new Coordinate(this._point.get_z().multiple(this._point.get_z()));
        Coordinate y = new Coordinate(this._point.get_x().multiple(this._point.get_x()));
        Coordinate z = new Coordinate(this._point.get_y().multiple(this._point.get_y()));
        return x.add(y.add(z));
    }

    /**
     * calculates a vectors length
     * @return double value
     */
    public double length()
    {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * divides a vector by its length
     * @return original vector
     */
    public Vector normalize ()   {
        double length = this.length();
        if (length == 0) {
            throw new ArithmeticException("divide by Zero");
        }
        this._point = this._point.divide(length);
        return this;
    }

    /**
     * divides a vector by its length
     * @return new vector
     */
    public Vector normalized ()  {
        return new Vector(this.normalize());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _point.equals(vector._point);
    }

    @Override
    public String toString() {
        return "_point=" + _point;
    }
}
