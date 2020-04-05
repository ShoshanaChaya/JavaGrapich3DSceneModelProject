package unittests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author Shoshana Chaya and Yael
 */

public class CylinderTest {
    // ============ Equivalence Partitions Tests ==============
    /**
     * Test method for {@link.geometries.Cylinder#getNormal(geometries.Cylinder)}.
     */
    @Test
    public void getNormal() {
        Cylinder c = new Cylinder(1, 5);
        assertEquals("Bad normal to tube", new Vector(-1, 0, 0), c.getNormal(new Point3D(2, 0 ,0)));
    }
}