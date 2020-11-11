package unittests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author Shoshana Chaya and Yael
 */

public class CylinderTest {
    // ============ Equivalence Partitions Tests ==============
    /**
     * Test method for getNormal of cylinder
     */
    @Test
    public void getNormal() {
        Cylinder c = new Cylinder(1, new Ray(new Point3D(0,0,1), new Vector(0,0,1)), 5);
        Vector v = c.getNormal(new Point3D(0, 0 ,1));
        assertEquals("Bad normal to tube", new Vector(0, 0, 1), v);
    }
}