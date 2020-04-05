package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class Triangle is the basic class representing a triangle
 * @author Shoshana Chaya and Yael
 */
public class Triangle extends Polygon
{
    /*********************** constructor ********************/
    public Triangle(Point3D firstPoint, Point3D secondPoint, Point3D thirdPoint)
    {
        super(firstPoint, secondPoint, thirdPoint);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return  "_vertices=" + _vertices +
                ", _plane=" + _plane;
    }
}
