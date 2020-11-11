package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class Cylinder is the basic class representing a cylinder
 * @author Shoshana Chaya and Yael
 */
public class Cylinder extends Tube
{
/*************** fields ****************/
        double height;
/******************** constructors ******************/
        /**
         * constructor that gets a radius and height
         * @param _radius double value
         * @param _ray ray value
         * @param _height double value
         */
        public Cylinder(double _radius, Ray _ray, double _height) {
                super(_radius, _ray);
                this.height = _height;
        }

        /**
         * constructor that gets a color, materual, radius, ray, height
         * @param emissionLight color value
         * @param _material material value
         * @param _radius double value
         * @param _ray ray value
         * @param _height double value
         */
        public Cylinder(Color emissionLight, Material _material, double _radius, Ray _ray, double _height) {
                super(emissionLight, _material, _radius, _ray);
                this.height = _height;
        }

        /**
        * copy constructor
        * @param other cylinder value
        */
        public Cylinder(Cylinder other) {
                super(other._radius, other.ray);
                this.height = other.height;
        }

/******************** getter ***********************/
        public double getHeight() {
                return height;
                }

        /**
        * returns the normal of th cylinder
        * @param point the point which from it we calculated the normal
        * @return normal
        **/
        @Override
        public Vector getNormal(Point3D point) {
                // calculates parameters for the function
                Ray r = new Ray(getRay());
                Vector v = new Vector(ray.get_vector());
                Point3D o = new Point3D(ray.get_point());

                double t;
                try { // try to built the normal
                        t = alignZero(point.subtract(o).dotProduct(v));
                }
                catch (IllegalArgumentException e) { // couldn't built the normal
                        return v;
                }

                if (t == 0 || isZero(height - t)) { // if the normal is valid
                        return v;
                }

                o = o.add(v.scale(t)); // calculation for the normal
                return point.subtract(o).normalize();
        }


        @Override
        public String toString() {
                return  "height=" + height +
                ", _radius=" + _radius;
                }

        /**
         * finds intersection points between a ray and a sphere
         * @param ray the ray which we find for it intersection points
         * @return list of geoPoints - intersections
         */
        @Override
        public List<GeoPoint> findIntersections(Ray ray) {
                List<GeoPoint> intersections = super.findIntersections(ray);
                if (intersections != null) { // it there are intersections
                        for (GeoPoint geoPoint : intersections) { // goes through all the GeopPoints in intersections
                                geoPoint._geometry = this; // sets the geometry of all the intersection points is cylinder
                        }
                }
                return intersections; // returns the intersection points
        }
}
