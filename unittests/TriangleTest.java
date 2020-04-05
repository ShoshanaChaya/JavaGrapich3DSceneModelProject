package unittests;

import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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

}