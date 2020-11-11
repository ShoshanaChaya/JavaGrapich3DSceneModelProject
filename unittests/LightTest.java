package unittests;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
/**
 * Unit tests for light.Elements class
 * @author Shoshana Chaya and Yael
 */
public class LightTest {
        /**
         * Test method for getIntensity for light
         */
    @Test
    public void get_intensity() {
        /**
         * Produce a picture of a sphere lighted by a directional light
         */
        //TC01 sphereDirectional
            Scene scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

            scene.addGeometries(
                    new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 100), 50, new Point3D(0, 0, 50)));

            scene.addLights(new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));

            ImageWriter imageWriter = new ImageWriter("sphereDirectional", 150, 150, 500, 500);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

        /**
         * Produce a picture of a sphere lighted by a point light
         */
        //TC02 spherePoint
            scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

            scene.addGeometries(
                    new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 100), 50, new Point3D(0, 0, 50)));

            scene.addLights(new PointLight(new Color(500, 300, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.000001));

            imageWriter = new ImageWriter("spherePoint", 150, 150, 500, 500);
            render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();


        /**
         * Produce a picture of a sphere lighted by a spot light
         */
        //TC03 sphereSpot
            scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

            scene.addGeometries(
                    new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 100), 50, new Point3D(0, 0, 50)));

            scene.addLights(new SpotLight(new Color(500, 300, 0), new Point3D(-50, 50, -50),
                    new Vector(1, -1, 2), 1, 0.00001, 0.00000001));

            imageWriter = new ImageWriter("sphereSpot", 150, 150, 500, 500);
            render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();


        /**
         * Produce a picture of a sphere lighted by a spot light, directional light and point light
         */
            //TC04 sphereMultipule
            scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

            scene.addGeometries(
                    new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.8, 100), 50, new Point3D(0, 0, 50)));

            scene.addLights(new PointLight(new Color(0, 300, 300), new Point3D(50, 50, 0), 1, 0.0000001, 0.00000001),
                    new SpotLight(new Color(50, 300, 300), new Point3D(100, -200, -200), new Vector(-1, 1, 1), 1, 0.00001, 0.00000001),
                    new DirectionalLight(new Color(50, 300, 100), new Vector(1, -1, 1))
            );

            imageWriter = new ImageWriter("sphereMultiple", 150, 150, 500, 500);
            render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();


        /**
         * Produce a picture of a two triangles lighted by a directional light
         */
        //TC05 trianglesDirectional
            scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

            scene.addGeometries(
                    new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                            new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                    new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                            new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

            scene.addLights(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

            imageWriter = new ImageWriter("trianglesDirectional", 200, 200, 500, 500);
            render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();


        /**
         * Produce a picture of a two triangles lighted by a point light
         */
        //TC06 trianglesPoint
            scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

            scene.addGeometries(
                    new Triangle(Color.BLACK, new Material(0.5, 0.5, 300),
                            new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                    new Triangle(Color.BLACK, new Material(0.5, 0.5, 300),
                            new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

            scene.addLights(new PointLight(new Color(500, 250, 250),
                    new Point3D(10, 10, 130),
                    1, 0.0005, 0.0005));

            imageWriter = new ImageWriter("trianglesPoint", 200, 200, 500, 500);
            render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();


        /**
         * Produce a picture of a two triangles lighted by a spot light
         */
        //TC07 trianglesSpot
            scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

            scene.addGeometries(
                    new Triangle(Color.BLACK, new Material(0.5, 0.5, 300),
                            new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                    new Triangle(Color.BLACK, new Material(0.5, 0.5, 300),
                            new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

            scene.addLights(new SpotLight(new Color(500, 250, 250),
                    new Point3D(10, 10, 130), new Vector(-2, 2, 1),
                    1, 0.0001, 0.000005));

            imageWriter = new ImageWriter("trianglesSpot", 200, 200, 500, 500);
            render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

        /**
         * Produce a picture of a triangle lighted by a spot light, directional light and point light
         */
            //TC08 Triangle Multipule
            scene = new Scene("Test scene");
            scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
            scene.setDistance(1000);
            scene.setBackground(Color.BLACK);
            scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

            scene.addGeometries(
                    new Triangle(Color.BLACK, new Material(0.5, 0.8, 100),
                            new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                    new Triangle(Color.BLACK, new Material(0.5, 0.8, 100),
                            new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

            scene.addLights(new PointLight(new Color(0, 300, 300), new Point3D(5, 5, 100), 1, 0.000001, 0.0005),
                    new SpotLight(new Color(50, 300,300), new Point3D(10, 0, 130), new Vector(-2, 2, 1), 1, 0.0002, 0.00001),
                    new DirectionalLight(new Color(50, 300, 100), new Vector(1, -1, 1))
            );

            imageWriter = new ImageWriter("trianglesMultiple", 200, 200, 500, 500);
            render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

    }
}

