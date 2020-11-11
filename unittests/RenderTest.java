package unittests;

import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTest {

    @Test
    public void renderImage() {
        /**
         * Produce a scene with basic 3D model and render it into a jpeg image with a
         * grid
         */
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(new Color(75, 127, 90));
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.printGrid(50, java.awt.Color.YELLOW);
        render.writeToImage();

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid and 4 triangles and a sphere
     */
        scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Color(java.awt.Color.BLUE),
                        new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),      // lower right
                new Triangle(
                        new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),    // upper right
                new Triangle(new Color(java.awt.Color.RED),
                        new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),    // lower left
                new Triangle(new Color(java.awt.Color.GREEN),
                        new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100))); // upper left

        imageWriter = new ImageWriter("color render test", 500, 500, 500, 500);
        render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.printGrid(50, java.awt.Color.WHITE);
        render.writeToImage();
    }


    /**
     * Test method for {@link.renderer.Render#getClosestPoint(renderer.Render)}.
     */
//    @Test
//    public void getClosestPoint() {
//        Scene scene = new Scene("Test scene");
//        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.setDistance(100);
//        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));
//        Sphere s = new Sphere(50, new Point3D(0, 0, 100));
//        scene.addGeometries(s);
//        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
//        Render render = new Render(imageWriter, scene);
//        Intersectable.GeoPoint p = new Intersectable.GeoPoint(s, render.getClosestPoint(scene.getGeometries().findIntersections(new Ray(new Point3D(0,0,0), new Vector(0,0,1)))).getPoint());
//        assertEquals("Wrong point", new Point3D(0, 0, 50), p);
//    }
}


