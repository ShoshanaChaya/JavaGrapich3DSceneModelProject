package unittests;

import geometries.Geometries;
import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Unit tests for geometries.Geometries class
 * @author Shoshana Chaya and Yael
 */
public class GeometriesTest {
    /**
     * Test method for findIntersection of geometries
     */
    @Test
    public void findIntsersections() {
        Geometries g = new Geometries();
        assertEquals(g.getIntersections().size(), 0);
        // =============== Boundary Values Tests ==================
        //TC01  checks if there aren't any shapes (0 points)
        assertNull(g.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(-1,0,0))));

        //TC02 none of the shapes have intersection points (0 points)
        g.add(new Triangle(new Point3D(1,0,0), new Point3D(0, 1, 0), new Point3D(0, 0, 1)));
        g.add(new Plane(new Point3D(1,0,0), new Point3D(0, 1, 0), new Point3D(0, 0, 1)));
        assertNull(g.findIntersections(new Ray(new Point3D(-0.1, 0, 0), new Vector(-1,0,0))));

        //TC03 only one shape has intersection points (more than 0 points)
        g.add(new Triangle(new Point3D(2,0,0), new Point3D(0, 2, 0), new Point3D(0, 0, 2)));
        assertNotEquals(g.findIntersections(new Ray(new Point3D(0.1, 0, 0), new Vector(1,0,0))), 0);
        assertNotNull(g.findIntersections(new Ray(new Point3D(0.1, 0, 0), new Vector(1,0,0))));

        //TC04  all the shapes have intersection points (at least one point)
        assertNotNull(g.findIntersections(new Ray(new Point3D(0.1, 0, 0), new Vector(0.5,0.5,0.5).normalize())));
        assertNotEquals(g.findIntersections(new Ray(new Point3D(0.1, 0, 0), new Vector(0.5,0.5,0.5).normalize())), 0);
        // ============ Equivalence Partitions Tests ==============

        //TC05  more than one shape has intersection points (at least one point)
        g.add(new Plane(new Point3D(2,0,0), new Point3D(0, 2, 0), new Point3D(0, 0, 2)));
        assertNotNull(g.findIntersections(new Ray(new Point3D(0.1, 0, 0), new Vector(1,0,0))));
        assertNotEquals(g.findIntersections(new Ray(new Point3D(0.1, 0, 0), new Vector(1,0,0))), 0);
    }
}