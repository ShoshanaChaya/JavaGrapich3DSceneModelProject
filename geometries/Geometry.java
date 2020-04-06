package geometries;

import primitives.Point3D;

/**
 * Abstract class Geometry is the basic class representing a geometry
 * @author Shoshana Chaya and Yael
 */
public interface Geometry extends Intersectable
{
    /**
     * calculates the normal to a given point
     * @param point point3D value
     * @return vector vector value
     */
    public primitives.Vector getNormal (Point3D point);
}
