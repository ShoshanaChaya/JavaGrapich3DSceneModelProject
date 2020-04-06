package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * An interface that represents the intersection points
 * @author Shoshana Chaya and Yael
 */
public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);
}
