package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Shoshana Chaya and Yael
 */
public class SphereTest {
    // ============ Equivalence Partitions Tests ==============
    /**
     * Test method for {@link.geometries.Sphere#getNormal(geometries.Sphere)}.
     */
    @Test
    public void getNormal() {
        Sphere s = new Sphere(1, new Point3D(1, 0 , 0));
        assertEquals("Bad normal to sphere", new Vector(1, 0, 0), s.getNormal(new Point3D(2, 0, 0)));
    }
}