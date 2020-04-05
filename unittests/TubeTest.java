package unittests;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
/**
 * Unit tests for geometries.Cylinder class
 * @author Shoshana Chaya and Yael
 */
public class TubeTest {
    // ============ Equivalence Partitions Tests ==============
    /**
     * Test method for {@link.geometries.Tube#getNormal(geometries.Tube)}.
     */
    @Test
    public void getNormal() {
        Tube t = new Tube(1, new Ray(new Point3D(1,0,0), new Vector(0,1,0)));
        assertEquals("Bad normal to tube", new Vector(-1, 0, 0), t.getNormal(new Point3D(2, 0, 0)));
    }
}