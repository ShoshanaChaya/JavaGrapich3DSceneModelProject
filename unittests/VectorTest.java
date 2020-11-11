package unittests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for primitives.Vector class
 * @author Shoshana Chaya and Yael
 */
public class VectorTest {
    Vector v1;
    Vector v2;
    Vector v3;

    /**
     * checks if the function add works correct
     */
    @Test
    public void add() {
        // ============ Equivalence Partitions Tests ==============
        v1 = new Vector(1.0, 1.0, 1.0);
        v2 = new Vector(-1.0, -1.0, -2.0);

        v1 = v1.add(v2);
        //Test that the add function works correct
        assertNotEquals(v1, new Vector(0.0,0.0,0.1));
        //Test that the add function works correct
        v2 = v2.add(v1);
        assertTrue(v2.equals(v2));
        // =============== Boundary Values Tests ==================
        // test zero vector for add opposite vectors
        try{
            v1.add(new Vector(-1, -1, -1));
            fail("the operator add didn't work");
        }
        catch(Exception e){

        }
    }
    /**
     * checks if the function subtract works correct
     */
    @Test
    public void subtract() {
        v1 = new Vector(1.0, 1.0, 1.0);
        v2 = new Vector(-1.0, -1.0, -1.0);
        // ============ Equivalence Partitions Tests ==============
        //Test that the substract vector works corect
        v1.subtract(v2);
        assertNotEquals(v1,new Vector(2.0,2.0,2.0));
        //Test that the substract vector works corect
        v2.subtract(v1);
        assertNotEquals(v2, new Vector(-3.0,-3.0,-3.0));

        // =============== Boundary Values Tests ==================
        // test zero vector for subtract opposite vectors
        try{
            v1.subtract(new Vector(1, 1, 1));
            fail("the operator subtract didn't work");
        }
        catch(Exception e){
        }
    }

    /**
     * checks if the function scale works correct
     */
    @Test
    public void scale() {
        v1 = new Vector(1.0, 1.0, 1.0);
        // ============ Equivalence Partitions Tests ==============
        //Test that the scale vector works corect
        v2 = new Vector(v1.scale(1));
        assertEquals(v1, v2);
        //Test that the scale vector works corect
        v1 = v1.scale(2);
        assertEquals(v1, new Vector(2.0,2.0,2.0));
        //Test that the scale vector works corect
        v1 = v1.scale(-2);
        assertEquals(v1, new Vector(-4.0,-4.0,-4.0));

        // =============== Boundary Values Tests ==================
        // test zero vector for scale opposite vectors
        try{
            v1.scale(0);
            fail("the operator scale didn't work");
        }
        catch(Exception e){
        }
    }

    /**
     * checks if the function dotProduct works correct
     */
    @Test
    public void dotProduct() {
        v1 = new Vector(1, 2, 3);
        v2 = new Vector(-2, -4, -6);
        v3 = new Vector(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        //Test that the dotProduct vector works corect
        double v = v1.dotProduct(v3);
        assertEquals(v, 0.0, 1e-10);
        //Test that the dotProduct vector works corect
        v = v1.dotProduct(v2) + 28;
        assertEquals(v,0.0, 1e-10);

        // =============== Boundary Values Tests ==================
        // test zero vector for dotProduct opposite vectors
        try{
            v = v1.dotProduct(new Vector(0,0,0));
            fail("the operator dotproduct didn't work");
        }
        catch(Exception e){
        }
    }

    /**
     * checks if the function add crossProduct correct
     */
    @Test
    public void crossProduct() {
        v1 = new Vector(1, 2, 3);
        v2 = new Vector(-2, -4, -6);
        v3 = new Vector(0, 3, -2);
        // =============== Boundary Values Tests ==================
        // test zero vector for crossProduct opposite vectors
        try { // test zero vector
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
        // ============ Equivalence Partitions Tests ==============
        //Test that the crossProduct vector works correct
        Vector v = v1.crossProduct(v3);
        assertEquals(v.length() - v1.length() * v3.length(), 0, 1e-10);
        //Test that the crossProduct vector works correct
        assertEquals(v.dotProduct(v1), 0, 1e-10);
        //Test that the crossProduct vector works correct
        assertEquals(v.dotProduct(v3), 0, 1e-10);
    }

    /**
     * checks if the function add lengthSquared correct
     */
    @Test
    public void lengthSquared() {
        v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        //Test that the length function works correct
        assertEquals(v1.lengthSquared() , 14, 1e-10);

        // =============== Boundary Values Tests ==================
        // test for length of 0
        assertEquals(v1.lengthSquared()-14 , 0, 1e-10);
    }

    /**
     * checks if the function length works correct
     */
    @Test
    public void length() {

        // ============ Equivalence Partitions Tests ==============
        //Test that the length function works correct
        assertEquals(new Vector(0, 3, 4).length() , 5, 1e-10);

        // =============== Boundary Values Tests ==================
        // test for length of 0
        assertEquals(new Vector(0, 3, 4).length() - 5, 0, 1e-10);
    }

    /**
     * checks if the function normalize works correct
     */
    @Test
    public void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        // ============ Equivalence Partitions Tests ==============
        //Test that the normalize function works correct
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals(vCopy, vCopyNormalize);
        //Test that the normalize function works correct
        assertEquals(vCopyNormalize.length() - 1, 0, 1e-10);

        // =============== Boundary Values Tests ==================
        // test for vector's values 0
        try{
            Vector vec4 = new Vector(0,0,0).normalize();
            fail("normalize function doesn't work properly");
        }
        catch (Exception e){
        }
    }

    /**
     * checks if the function normalized works correct
     */
    @Test
    public void normalized() {
        Vector vec5 = new Vector(1, 2, 3);
        Vector u = vec5.normalized();
        // ============ Equivalence Partitions Tests ==============
        //Test that the normalized function works correct
        assertEquals(u, vec5);

        // =============== Boundary Values Tests ==================
        // test for vector's values 0
        try{
            Vector vec4 = new Vector(0,0,0).normalized();
            fail("normalize function doesn't work properly");
        }
        catch (Exception e){
        }
    }
}