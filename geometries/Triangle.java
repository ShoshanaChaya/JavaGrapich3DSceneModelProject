package geometries;

import primitives.*;

import java.util.List;

/**
 * Class Triangle is the basic class representing a triangle
 * @author Shoshana Chaya and Yael
 */
public class Triangle extends Polygon
{
    /*********************** constructor ********************/
    /**
     * constructor that gets 3 points
     * @param firstPoint point of triangle
     * @param secondPoint point of triangle
     * @param thirdPoint point of triangle
     */
    public Triangle(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint)
    {
        super(firstPoint, secondPoint, thirdPoint);
    }

    /**
     * constructor that gets a color, material, and 3 points
     * @param emissionLight color value
     * @param material material value
     * @param p1 point of triangle
     * @param p2 point of triangle
     * @param p3 point of triangle
     */
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,material,p1,p2,p3);
    }

    /**
     * constructor that gets a color and 3 points
     * @param emissionLight color value
     * @param p1 point of triangle
     * @param p2 point of triangle
     * @param p3 point of triangle
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,p1, p2, p3);
    }

    /**
     * calculates the normal to a given point
     * @param point point that from it starts the normal
     * @return normal
     */
    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return  "_vertices=" + _vertices +
                ", _plane=" + _plane;
    }

    /**
     * finds intersections of the triangle
     * @param ray ray which does the intersections
     * @return a list of intersection points
     */
    public List<GeoPoint> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
