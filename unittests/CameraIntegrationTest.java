package unittests;

import elements.Camera;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing construct rays through geometries
 * @author Shoshana Chaya and Yael
 */
public class CameraIntegrationTest {

    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
    Plane pln = new Plane(new Point3D(0,0,2), new Point3D(0, -1, 2), new Point3D(1,5,2));
    Triangle trg1 = new Triangle(new Point3D(0,-1,2), new Point3D(1,1,2), new Point3D(-1, 1,2));
    Triangle trg2 = new Triangle(new Point3D(0,-20,2), new Point3D(1,1,2), new Point3D(-1, 1,2));

    @Test
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    public void constructRayThroughPixel() {
        //TC01: a sphere in front of the view plane (2 points)
        Sphere sph = new Sphere(1, new Point3D(0, 0, 3));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        // the view plane width and height:
        int Nx = 3;
        int Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 2, count);

        //TC02: the ray of the camera goes through the sphere and constructs also the other side of it (18 points)
        sph = new Sphere(2.5, new Point3D(0, 0, 2.5));

        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 18, count);

        //TC03: only 5 rays succeed to construct through sphere and construct through the other side too (10 points)
        sph = new Sphere(2, new Point3D(0, 0, 2));

        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 10, count);

        //TC04: the camera and view plane are in the sphere therefor all 9 rays succeed to construct the sphere once (9 points)
        sph = new Sphere(4, new Point3D(0, 0, 0));

        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 9, count);

        //TC05: the sphere is behind the view plane therefor there aren't any intersection points (0 points)
        sph = new Sphere(0.1, new Point3D(0, 0, -1));

        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 0, count);

        //TC06: the plane is parallel to the view plane (9 points)
        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = pln.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 9, count);


        //TC07: the plane is diagonal to the view plane (9 points)
        pln = new Plane(new Point3D(1,-1,1), new Point3D(10, 0, 3), new Point3D(1,5,5));

        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = pln.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 9, count);


        //TC08: the plane is diagonal to the view plane (6 points)
        pln = new Plane(new Point3D(1,-1,13), new Point3D(5, 0, 15), new Point3D(1,5,30));

        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = pln.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 6, count);


        //TC09: the triangle is parallel to the view plane and relatively small - the vertex of the triangle is under the view plane (1 point)
        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = trg1.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 1, count);


        //TC010: the triangle is parallel to the view plane and relatively big - the vertex of the triangle is above the view plane (2 point)
        count = 0;
        // the view plane width and height:
        Nx = 3;
        Ny = 3;
        // going through the view plane and counting the amount of intersection points:
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = trg2.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("wrong amount", 2, count);
    }

}