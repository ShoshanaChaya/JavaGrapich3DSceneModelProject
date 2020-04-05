package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Plane class
 * @author Shoshana Chaya and Yael
 */
public class PlaneTest {
    /**
     * Test method for {@link.geometries.Plane#getNormal(geometries.Plane)}.
     */
    @Test
    public void getNormal() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: There is a simple single test here
            Plane pl = new Plane(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
            assertEquals("Bad normal to plane", new Vector(0, 0, 1), pl.getNormal(new Point3D(1, 0, 0)));
        }
}