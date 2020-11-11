package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Sphere;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class project1 {

    @Test
    public void project1() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(12,20,23));
        scene.setAmbientLight(new AmbientLight(new Color(0,0,0), 1));

        Color c = new Color(400,200,0);
        Material m = new Material(0.5, 0.5, 100, 0.6, 0.6);
        scene.addGeometries(
                new Sphere(new Color(0,0,0), new Material(0.5, 1, 1000), 100, new Point3D(100, -140, 50)),//sun
                new Sphere(new Color(0, 0, 0), new Material(0.5,  0, 100), 100, new Point3D(50, 125, 50)),//moon
                new Sphere(new Color(0,0,0), new Material(0.5, 0.5, 100, 1, 0), 5, new Point3D(0, 0, 0)),//middle medium star
                new Sphere(new Color(0,0,10), new Material(1, 0, 100, 1, 0), 3.5, new Point3D(-55, 50, 0)),//middle right star
                new Sphere(new Color(0,0,0), new Material(0.5, 0.5, 100, 0, 0.6), 7.5, new Point3D(-45, -45, 0)),//top big star
                new Sphere(new Color(15,15,0), new Material(0.5, 0.5, 1000,0, 0.6), 4, new Point3D(-35, -18, 0)),//down small star
                new Sphere(new Color(0,10,10), new Material(0.5, 0.5, 100, 1, 0), 6, new Point3D(-30, 20, 0)),//down right star
                new Sphere(new Color(15,0,0), new Material(0.5, 0.5, 50, 0.6,0 ), 15, new Point3D(25, -85, 1400)),//right red star
                new Sphere(c, m, 1, new Point3D(-45, 45, 0)),
                new Sphere(c, m, 0.5, new Point3D(-20, 36, 0)),
                new Sphere(c, m, 0.75, new Point3D(-47, 30, 0)),
                new Sphere(c, m, 0.5, new Point3D(-60, 16, 0)),
                new Sphere(c, m, 0.75, new Point3D(-60, 56, 0)),
                new Sphere(c, m, 0.75, new Point3D(-20, 6, 0)),
                new Sphere(c, m, 0.5, new Point3D(-40, 36, 0)),
                new Sphere(c, m, 0.75, new Point3D(-60, 40, 0)),
                new Sphere(c, m, 0.5, new Point3D(-45, 56, 0)),
                new Sphere(c, m, 0.75, new Point3D(-50, 65, 0)),
                new Sphere(c, m, 0.9, new Point3D(-70, 70, 0)),
                new Sphere(c, m, 0.75, new Point3D(-40, 6, 0)),
                new Sphere(c, m, 0.3, new Point3D(70, 20, 0)),
                new Sphere(c, m, 0.5, new Point3D(50, -10, 0)),
                new Sphere(c, m, 0.75, new Point3D(60, -15, 0)),
                new Sphere(c, m, 0.3, new Point3D(55, 5, 0)),
                new Sphere(c, m, 0.75, new Point3D(25, 15, 100)),
                new Sphere(c, m, 0.3, new Point3D(0, 20, 88)),
                new Sphere(c, m, 0.75, new Point3D(35, -5, 0)),
                new Sphere(c, m, 0.75, new Point3D(-60, -15, 0)),
                new Sphere(c, m, 0.4, new Point3D(-30, -10, 0)),
                new Sphere(c, m, 0.75, new Point3D(-45, -7.5, 0)),
                new Sphere(c, m, 0.9, new Point3D(-65, -45, 0)),
                new Sphere(c, m, 0.75, new Point3D(-62, -35, 0)),
                new Sphere(c, m, 0.5, new Point3D(-45, -20, 0)),
                new Sphere(c, m, 0.75, new Point3D(-7.5, -15, 0)),
                new Sphere(c, m, 0.65, new Point3D(-7.5, -5, 0)),
                new Sphere(c, m, 0.75, new Point3D(15, -2.5, 0)),
                new Sphere(c, m, 0.5, new Point3D(5, -15, 0)),
                new Sphere(c, m, 0.75, new Point3D(25, -10, 0)),
                new Sphere(c, m, 0.3, new Point3D(40, 20, 100)),
                new Sphere(c, m, 0.75, new Point3D(42.5, -20, 0)),
                new Sphere(c, m, 0.5, new Point3D(22.5, -22, 0)),
                new Sphere(c, m, 0.75, new Point3D(30, -30, 0)),
                new Sphere(c, m, 0.9, new Point3D(50, -35, 100)),
                new Sphere(c, m, 0.25, new Point3D(25, -40, 60)),
                new Sphere(c, m, 0.75, new Point3D(-20, -40, 0)),
                new Sphere(c, m, 0.5, new Point3D(-15, -30, 0)),
                new Sphere(c, m, 0.75, new Point3D(-35, -35, 0)),
                new Sphere(c, m, 0.75, new Point3D(-10, -60, 0)),
                new Sphere(c, m, 0.3, new Point3D(-2.5, -50, 0)),
                new Sphere(c, m, 0.85, new Point3D(5, -55, 0)),
                new Sphere(c, m, 0.75, new Point3D(15, -47.5, 0)),
                new Sphere(c, m, 0.75, new Point3D(-30, -65, 0)),
                new Sphere(c, m, 0.75, new Point3D(-32.5, -55.5, 0)),
                new Sphere(c, m, 0.35, new Point3D(-55, -67, 0)),
                new Sphere(c, m, 0.75, new Point3D(-60, -55, 0)),
                new Sphere(c, m, 0.5, new Point3D(-65, -65, 0)),
                new Sphere(c, m, 0.75, new Point3D(10, -70, 80)),
                new Sphere(c, m, 0.75, new Point3D(55, -40, 60)),
                new Sphere(c, m, 0.6, new Point3D(-55, -1, 0)),
                new Sphere(c, m, 0.75, new Point3D(-15, 20, 0)),
                new Sphere(c, m, 0.55, new Point3D(10, -25, 0)),
                new Sphere(c, m, 0.9, new Point3D(0, -35, 0)));
                scene.addLights(new DirectionalLight(new Color(187, 228, 250), new Vector(-2, 1.5, 1)),
                        new SpotLight(new Color(1200,500, 0), new Point3D(40, 50, -115),
                                new Vector(1, -1, 0), 1, 0.00001, 0.00000001),
                        new SpotLight(new Color(1200,500, 0), new Point3D(63.5, 25, -50),
                                new Vector(-0.5, 1, -50), 1, 0.01, 0.00000001),
                        new SpotLight(new Color(1200,500, 0), new Point3D(63.5, 25, 10), new Vector(1, -1, 0), 1, 0.01, 0.0001)
                       );
        ImageWriter imageWriter = new ImageWriter("project1", 150, 150, 1000, 1000);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.writeToImage();
    }
}