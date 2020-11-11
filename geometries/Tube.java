package geometries;

import primitives.*;

import java.util.List;

/**
 * Class Tube is the basic class representing a tube
 * @author Shoshana Chaya and Yael
 */
public class Tube extends RadialGeometry
{
    /************* field ****************/
    Ray ray;

/************* constructor ***********/
    /**
     * constructor that gets a radius and ray
     * @param _radius radius
     * @param _ray ray value
     */
    public Tube(double _radius, Ray _ray) {
        this(Color.BLACK, new Material(0, 0, 0), _radius, _ray);
    }

    /**
     * constructor that gets a color, material, radius and ray
     * @param emissionLight color value
     * @param _material material value
     * @param _radius radius
     * @param _ray ray value
     */
    public Tube(Color emissionLight, Material _material, double _radius, Ray _ray) {
        super(Color.BLACK, _radius);
        this._material = _material;
        this.ray = new Ray(_ray.get_point(), _ray.get_vector().normalize());
    }

    /**
     * constructor that gets a color, radius and ray
     * @param emissionLight color value
     * @param _radius radius
     * @param _ray ray value
     */
    public Tube(Color emissionLight, double _radius, Ray _ray) {
        this(emissionLight, new Material(0, 0, 0), _radius, _ray);
    }

    /************ getter ******************/
    public Ray getRay() {
        return ray;
    }

    @Override
    public String toString() {
        return  "ray=" + ray +
                ", _radius=" + _radius;
    }

    /**
     * calculates the normal to a given point
     * @param point point that from it starts the normal
     * @return vector normal
     */
    @Override
    public Vector getNormal(Point3D point) {
        // fields saved for the function use
        Point3D o = ray.get_point();
        Vector v = ray.get_vector();

        Vector vector1 = point.subtract(o);

        double projection = vector1.dotProduct(v);
        if(projection == 0) // projection of P-O on the ray
        {
            o.add(v.scale(projection));
        }

        Vector check = point.subtract(o);
        return check.normalize(); // returns the normal
    }

    /**
     * finds intersections of the tube
     * @param ray the ray which we find intersection points with
     * @param maxDistance the maximum distance
     * @return a list of intersection points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        return null;
    }

    //    /**
//     * finds intersections of the tube
//     * @param anotherray the ray which we find intersection points with
//     * @param maxDistance the maximum distance
//     * @return a list of intersection points
//     */
//    @Override
//    public List<GeoPoint> findIntersections(Ray anotherray, double maxDistance) {
//        Point3D P = anotherray.get_point();
//        Point3D _point = this.ray.get_point();
//
//        Vector V = anotherray.get_vector(),
//                Va = this.ray.get_vector(),
//                DeltaP = new Vector(P.subtract(_point)),
//                temp_for_use1, temp_for_use2;
//
//        double V_dot_Va = V.dotProduct(Va),
//                DeltaP_dot_Va = DeltaP.dotProduct(Va);
//
//        temp_for_use1 = V.subtract(Va.scale(V_dot_Va));
//        temp_for_use2 = DeltaP.subtract(Va.scale(DeltaP_dot_Va));
//
//        double A = temp_for_use1.dotProduct(temp_for_use1);
//        double B = 2 * V.subtract(Va.scale(V_dot_Va)).dotProduct(DeltaP.subtract(Va.scale(DeltaP_dot_Va)));
//        double C = temp_for_use2.dotProduct(temp_for_use2) - _radius * _radius;
//        double desc = alignZero(B * B - 4 * A * C);
//
//        if (desc < 0) {//No solution
//            return null;
//        }
//
//        double t1 = (-B + Math.sqrt(desc)) / (2 * A),
//                t2 = (-B - Math.sqrt(desc)) / (2 * A);
//
//        if (desc == 0) {//One solution
//            if (-B / (2 * A) < 0) {
//                return null;
//            } else {
//                return List.of(new GeoPoint(this, P.add(V.scale(-B / (2 * A)))));
//            }
//        } else if (t1 < 0 && t2 < 0) {
//            return null;
//        } else if (t1 < 0 && t2 > 0) {
//            return List.of(new GeoPoint(this, P.add(V.scale(t2))));
//        } else if (t1 > 0 && t2 < 0) {
//            return List.of(new GeoPoint(this, P.add(V.scale(t1))));
//        } else {
//            return List.of(
//                    new GeoPoint(this, P.add(V.scale(t1))),
//                    new GeoPoint(this, P.add(V.scale(t2)))
//            );
//        }
//    }
}
