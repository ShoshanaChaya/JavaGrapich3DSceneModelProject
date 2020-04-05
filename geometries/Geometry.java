package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Abstract class Geometry is the basic class representing a geometry
 * @author Shoshana Chaya and Yael
 */
public interface Geometry
{
    /**
     * calculates the normal to a given point
     * @param point
     * @return vector
     */
    public primitives.Vector getNormal (Point3D point);
}
