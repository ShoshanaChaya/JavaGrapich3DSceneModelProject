package unittests;

import geometries.Intersectable;
import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Unit tests for geometries.Plane class
 * @author Shoshana Chaya and Yael
 */
public class PlaneTest {
    /**
     * Test method for getNormal of plane
     */
    @Test
    public void getNormal() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: There is a simple single test here
            Plane pl = new Plane(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
            assertEquals("Bad normal to plane", new Vector(0, 0, 1), pl.getNormal(new Point3D(1, 0, 0)));
        }

    /**
     * Test method for findIntersections of plane
     */
    @Test
    public void findIntersections() {
        Plane p= new Plane (new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(1,1,0));
        List<Intersectable.GeoPoint> result;
        // ============ Equivalence Partitions Tests ==============
        //TC01 Ray's line inside plane (1 point)
        result = p.findIntersections(new Ray(new Point3D(0.99,0.99,3), new Vector(0,0,-1).normalize()));
        assertEquals("Wrong number of points", 1, result.size());
        //TC02 Ray starts on the plane
        result = p.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,0,-1).normalize()));
        assertEquals("Wrong number of points", null, result);
        // =============== Boundary Values Tests ==================
        //TC04 on the plane (0 points)
        result=p.findIntersections(new Ray(new Point3D(0.99,0.99,0), new Vector(1,1,0).normalize()));
        assertEquals("Wrong number of points", null, result);
        //TC05 The ray is parallel to the plane(1 point)
        result=p.findIntersections(new Ray(new Point3D(0.99,0.99,3), new Vector(1,1,0).normalize()));
        assertEquals("Wrong number of points", null, result);


    }
}