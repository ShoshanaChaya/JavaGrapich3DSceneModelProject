package unittests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Unit tests for geometries.Triangle class
 * @author Shoshana Chaya and Yael
 */
public class TriangleTest {
    /**
     * Test method for {@link.geometries.Triangle#getNormal(geometries.Triangle)}.
     */
    @Test
    public void getNormal() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: There is a simple single test here
            Triangle tr = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
            assertEquals("Bad normal to trinagle", new Vector(1, 1, 1), tr.getNormal(new Point3D(0, 0, 1)));
    }
    /**
     * Test method for {@link.geometries.Triangle#findIntsersections(geometries.Triangle)}.
     */
    @Test
    public void findIntsersections()
    {
        Triangle t = new Triangle(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(1,1,0));
        List<Point3D> result;

        // ============ Equivalence Partitions Tests ==============
        //TC01 Ray's line inside triangle (1 point)
        result = t.findIntersections(new Ray(new Point3D(0.99,0.99,3), new Vector(0,0,-1).normalize()));
        assertEquals("Wrong number of points", 1, result.size());
        //TC02 Ray's line outside triangle, against edge (0 points)
        result = t.findIntersections(new Ray(new Point3D(2,1.5,3), new Vector(0,0,-1).normalize()));
        assertEquals("Wrong number of points", null, result);
        //TC03 Ray's line outside triangle, against vertex (0 points)
        result = t.findIntersections(new Ray(new Point3D(2,2,3), new Vector(0,0,-1).normalize()));
        assertEquals("Wrong number of points", null, result);

        // =============== Boundary Values Tests ==================
        //The ray begins before the triangle
        t = new Triangle(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(1,1,0));
        //TC04 on the edge (1 point)
        result = t.findIntersections(new Ray(new Point3D(0.9,0.3,-0.3), new Vector(0,0,1).normalize()));
        assertEquals("Wrong number of points", 1, result.size());
        //TC05 in vertex (1 point)
        result = t.findIntersections(new Ray(new Point3D(0.9,0.3,1), new Vector(0,0,-1).normalize()));
        assertEquals("Wrong number of points", 1, result.size());
        //TC06 on edges continuation (0 point)
        result = t.findIntersections(new Ray(new Point3D(1,2,2), new Vector(0,0,-1).normalize()));
        assertEquals("Wrong number of points", null, result);


    }
}