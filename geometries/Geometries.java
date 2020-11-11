package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
/**
 * Class Plane is the basic class representing a geometries
 * @author Shoshana Chaya and Yael
 */
public class Geometries implements Intersectable {
    /**************** fields *************/
    List<Intersectable> intersections;

    /**
     * default constructor
     */
    public Geometries() {
        this.intersections = new LinkedList<Intersectable>();
    }

    /**
     * constructor that gets an unlimited amount of geometries
     * @param geometries Intersectable geometries
     */
    public Geometries(Intersectable... geometries){
        for (Intersectable g: geometries) {
            intersections.add(g);
        }
    }

    /**
     * adds the geometries to the list of geometries
     * @param geometries Intersectable geometries
     */
    public void add(Intersectable... geometries){
        for (Intersectable g: geometries) {
            intersections.add(g);
        }
    }
/**************** getter ******************/
    public List<Intersectable> getIntersections() {
        return intersections;
    }

    /**
     * finds intersections of the geometries
     * @param ray ray value
     * @return a list of intersection points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersection = null;
        for (Intersectable geo : intersections) { // goes through all the intersection points in intersections
            List<GeoPoint> tempIntersections = geo.findIntersections(ray, maxDistance);
            if (tempIntersections != null) {
                if (intersection == null) { // if it isn't an intersection
                    intersection = new LinkedList<>();
                }
                intersection.addAll(tempIntersections);
            }
        }
        return intersection; // return the intersections

    }
}
