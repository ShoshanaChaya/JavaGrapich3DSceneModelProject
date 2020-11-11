package primitives;

import static primitives.Util.*;

/**
 * Class Coordinate is the basic class representing a coordinate for Cartesian
 * coordinate system. The class is based on Util controlling the accuracy.
 *
 * @author Dan Zilberstein
 */
public final class Coordinate {
    /**
     * Coordinate value, intentionally "package-friendly" due to performance
     * constraints
     */
    final double _coord;

    /**
     * Coordinate constructor receiving a coordinate value
     *
     * @param coord coordinate value
     */
    public Coordinate(double coord) {
        // if it too close to zero make it zero
        _coord = alignZero(coord);
    }

    /**
     * Copy constructor for coordinate
     *
     * @param other
     */
    public Coordinate(Coordinate other) {
        _coord = other._coord;
    }

    /**
     * Coordinate value getter
     *
     * @return coordinate value
     */
    public double get() {
        return _coord;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return isZero(_coord - ((Coordinate)obj)._coord);
    }
    /**
     * subtract one point from the other
     * @param cord
     * @return vector
     */
    public Coordinate subtract(Coordinate cord)
    {
        return new Coordinate(_coord - cord._coord);
    }
    /**
     * adds one point to another
     * @param cord
     * @return Point3D
     */
    public double add (Coordinate cord)
    {
        return _coord + cord._coord;
    }

    /**
     * adds one double to another
     * @param cord double value
     * @return double value
     */
    public double add (double cord)
    {
        return _coord + cord;
    }
    /**
     * multiplies one point to another
     * @param cord
     * @return Point3D
     */
    public Coordinate multiple (Coordinate cord)
    {
        return new Coordinate( _coord * cord._coord);
    }

    /**
     * divides a point by a number
     * @param cord double value
     * @return coordinate value
     */
    public Coordinate divide (double cord)
    {
        return new Coordinate(_coord / cord);
    }

    /**
     * checks if a number is bigger than another
     * @param cord coordinate value
     * @return boolean value
     */
    public boolean bigger(Coordinate cord){
        if (_coord > cord._coord){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + _coord;
    }
}
