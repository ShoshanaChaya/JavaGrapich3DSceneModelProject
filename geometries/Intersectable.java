package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * An interface that represents the intersection points
 * @author Shoshana Chaya and Yael
 */
public interface Intersectable {
    /**
     * Class GeoPoint is the class representing a geometry with a point
     * @author Shoshana Chaya and Yael
     */
    public static class GeoPoint {
/*********** fields *****************/
        public Geometry _geometry;
        public Point3D point;

        /**
         * constructor that gets a geometry and a point
         * @param _geometry geometry value
         * @param pt intersection point
         */
        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            point = pt;
        }
/*************** getter ********************/
        public Geometry get_geometry() {
            return _geometry;
        }

        public Point3D getPoint() {
            return point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(get_geometry(), geoPoint.get_geometry()) && this.getPoint().equals(geoPoint.getPoint());
        }
    }
    /**
     * finds intersection points between a ray and a geometry
     * @param ray ray value
     * @return list of geoPoints - intersections
     */
    default List<GeoPoint> findIntersections(Ray ray){
        return findIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * finds intersection points between a ray and a geometry
     * @param ray ray value
     * @param maxDistance double value
     * @return list of geoPoints - intersections
     */
    List<GeoPoint> findIntersections(Ray ray, double maxDistance);
}
