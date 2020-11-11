package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.FlatGeometry;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Render is the class representing a render
 * @author Shoshana Chaya and Yael
*/
public class Render {

    /*********************** fields **************/
    private Scene _scene;
    private ImageWriter _imageWriter;
    private double _supersamplingDensity = 0.1d;
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private int _threads = 1;
    private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private boolean _print = false; // printing progress percentage
    private boolean _adaptiveSupersampling = true;
    private int MAX_ADAPTIVERECURSION = 4;

    /**
     * constructor that gets the parameters imageWriter, scene
     * @param imageWriter imageWriter value
     * @param scene scene value
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene=   scene;
    }

    //************** getters ****************//
    public Scene get_scene() {
        return _scene;
    }

    public ImageWriter get_imageWriter() {
        return _imageWriter;
    }

    public double getSupersamplingDensity() {
        return _supersamplingDensity;
    }

    public void setSupersamplingDensity(double supersamplingDensity) {
        _supersamplingDensity = supersamplingDensity;
    }

    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less SPARE (2) is taken
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0) throw new IllegalArgumentException("Multithreading must be 0 or higher");
        if (threads != 0) {
            _threads = threads;
        }
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            _threads = cores <= 2 ? 1 : cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true; return this;
    }

    /**
     * builds a image according to the parameters of the class
     */
    public void renderImage() {
        //saves the fields of the class
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color temp = _scene.getBackground().getColor();
        Color background = new Color(temp);
        AmbientLight ambientLight = _scene.getAmbientLight();
        double distance = _scene.getDistance();
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        final Pixel thePixel = new Pixel(Ny, Nx); // Main pixel management object
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) { // create all threads
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel(); // Auxiliary thread’s pixel object
                while (thePixel.nextPixel(pixel)) {
                    if (_supersamplingDensity == 0d) { // before the improvement
                            Ray ray = camera.constructRayThroughPixel(Nx, Ny, pixel.row, pixel.col, distance, width, height);
                            GeoPoint intersectionPoints = findClosestIntersection(ray);
                            _imageWriter.writePixel(pixel.row, pixel.col, intersectionPoints == null ? _scene.getBackground().getColor() : calcColor(intersectionPoints, ray).getColor());
                    }
                    else { // after the improvement
                        if (_adaptiveSupersampling == true) { // for the adaptive superSampling
                            Color result = adaptiveSamplingRecursion(camera, distance, Nx, Ny, width, height, pixel,
                                    width / Nx / 2, height / Ny / 2, -width / Nx / 2, height / Ny / 2, MAX_ADAPTIVERECURSION);
                            _imageWriter.writePixel(pixel.col, pixel.row, result.getColor());
                        }
                        else { // superSampling
                            LinkedList<Ray> rayBeam = camera.constructRayBeamThroughPixel(Nx, Ny, pixel.row, pixel.col, distance, width, height, 200);
                            Color color = Color.BLACK;
                            double counter = 0;
                            // goes through each ray from the rayBeam
                            for (Ray r : rayBeam) {
                                GeoPoint intersectionPoints = findClosestIntersection(r);
                                if (intersectionPoints == null) { // if there aren't any intersection points
                                    color = color.add(background);
                                }
                                else {
                                    counter++;
                                    color = color.add(calcColor(intersectionPoints, r)); // ads the intersection points
                                }
                            }
                            Color c;
                            if (counter == 0)// if there aren't any intersection points
                                c = new Color(background);
                            else {
                                c = color;
                                c = c.scale(1d / rayBeam.size()); // calculates the average
                            }
                            _imageWriter.writePixel(pixel.row, pixel.col, c.getColor());
                        }
                    }
                }});
        }
        for (Thread thread : threads) thread.start(); // Start all the threads
        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            }
            catch (Exception e) {}
        }
        if (_print) System.out.printf("\r100%%\n"); // Print 100%
    }

    /**
     * calculates the color by using a recursion
     * @param camera the camera we use for the scene
     * @param distance the distance between the camera and view plane
     * @param nx number of pixels on the x axis
     * @param ny number of pixels on the y axis
     * @param width the width of the picture
     * @param height the height of the picture
     * @param pixel a single pixel which we are currently in
     * @param TX top corner of x
     * @param TY top corner of y
     * @param BX bottom corner of x
     * @param BY bottom corner of y
     * @param level the level of the recursive
     * @return color of the pixel that was sent
     */
    private Color adaptiveSamplingRecursion(Camera camera, double distance, int nx, int ny, double width, double height, Pixel pixel, double TX, double TY, double BX, double BY ,int level) {
        // creates a list of rays
        List<Ray> rays = camera.constructAdaptiveRayBeamThroughPixel(nx, ny, pixel.col, pixel.row, distance, width, height, TX, TY, BX, BY);
        Ray center = rays.get(0); // the center
        Color centerColor;
        Color TLColor ;
        Color TRColor ;
        Color BLColor ;
        Color BRColor ;
        if(findClosestIntersection(center) == null) { // if there aren't any intersections
            centerColor = _scene.getBackground();
        }
        else { // calculates the color
            centerColor = calcColor(findClosestIntersection(center), center);
        }
        Ray TL = rays.get(1);
        if(findClosestIntersection(TL) == null) {// if there aren't any intersections
            TLColor = _scene.getBackground();
        }
        else {// calculates the color
            TLColor = calcColor(findClosestIntersection(TL), TL);
        }
        Ray TR = rays.get(2);
        if(findClosestIntersection(TR) == null) {// if there aren't any intersections
            TRColor = _scene.getBackground();
        }
        else {// calculates the color
            TRColor = calcColor(findClosestIntersection(TR), TR);
        }
        Ray BL = rays.get(3);
        if(findClosestIntersection(BL) == null) {// if there aren't any intersections
            BLColor = _scene.getBackground();
        }
        else {// calculates the color
            BLColor = calcColor(findClosestIntersection(BL), BL);
        }
        Ray BR = rays.get(4);
        if(findClosestIntersection(BR) == null) {// if there aren't any intersections
            BRColor = _scene.getBackground();
        }
        else {// calculates the color
            BRColor = calcColor(findClosestIntersection(BR), BR);
        }
        if (level == 1) { // end of recursive
            centerColor = centerColor.add(TLColor, TRColor, BLColor, BRColor);
            return centerColor.reduce(5); // return the calculated color
        }
        // if the color in all the corners and center are equel-  rturn the color
        if (TLColor.equals(centerColor) && TRColor.equals(centerColor) && BLColor.equals(centerColor) && BRColor.equals(centerColor)) {
            return centerColor;
        }
        else {
            // if the color in each corner isn't equel to the center color - call the recursive function again with that corner
            if (!TLColor.equals(centerColor)) {
                TLColor = adaptiveSamplingRecursion(camera, distance, nx, ny, width, height, pixel, TX, TY, (TX+BX) / 2, (TY+BY) / 2, level - 1);
            }
            if (!TRColor.equals(centerColor)) {
                TRColor = adaptiveSamplingRecursion(camera, distance, nx, ny, width, height, pixel,  TX,(TY+BY)/2, (TX+BX)/2, BY, level - 1);
            }
            if (!BLColor.equals(centerColor)) {
                BLColor = adaptiveSamplingRecursion(camera, distance, nx, ny, width, height, pixel, (TX+BX)/2 ,TY, BX, (TY+BY)/2, level - 1);
            }
            if (!BRColor.equals(centerColor)) {
                BRColor = adaptiveSamplingRecursion(camera, distance, nx, ny, width, height, pixel, (TX+BX) / 2, (TY+BY) / 2, BX, BY, level - 1);
            }
        }
        centerColor = centerColor.add(TLColor, TRColor, BLColor, BRColor); // adds the colors
        return centerColor.reduce(5); // returns the calculated color
    }


    /**
     * Finding the closest point to the P0 of the camera.
     * @param  intersectionPoints list of points, the function should find from this list the closet point to P0 of the camera in the scene.
     * @return  the closest point to the camera
     */
    public GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints)
    {
        double distance = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().get_p0();
        GeoPoint minDistancePoint = null;
        // goes through each GeoPoint in intersectionPoints
        for (GeoPoint Geopoint : intersectionPoints)
            if (p0.distance(Geopoint.point) < distance) { // o\if the point is closer than the saved point
                minDistancePoint = new GeoPoint(Geopoint.get_geometry(), Geopoint.point);
                distance = p0.distance(Geopoint.point);
            }
        return minDistancePoint; // returns the closest point

    }

    /**
     * Printing the grid with a fixed interval between lines
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color colorsep)
     {
         for (int i = 0; i < _imageWriter.getWidth(); i++) {
             for (int j = 0; j < _imageWriter.getHeight(); j++) {
                 if (i % interval == 0 || j % interval == 0) {
                     _imageWriter.writePixel(i, j, colorsep);
                 }
             }
         }
    }

    /**
     * writes to an image
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * a function to call the other calcColor
     * @param gp geoPoint value
     * @param ray ray value
     * @return color value
     */
    private Color calcColor(GeoPoint gp, Ray ray)
    {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1d).add(_scene.getAmbientLight().get_intensity());
    }

    /**
     * Calculate the color intensity in a point
     * @param intersection the point for which the color is required
     * @return the color intensity
     */

    /**
     * Calculate the color intensity in a point
     * @param intersection the point for which the color is required
     * @param inRay the ray that has on it the point which we check it's color
     * @param level the recursive level
     * @param k the factor k
     * @return the color intensity
     */
    private Color calcColor(GeoPoint intersection, Ray inRay, int level, double k)
    {
        // calculates the fields that the function uses
        Color color = intersection.get_geometry().get_emission();
        Vector v = intersection.point.subtract(_scene.getCamera().get_p0()).normalize();
        Vector n = intersection.get_geometry().getNormal(intersection.point);
        color = color.add(intersection.get_geometry().get_emission());
        Material material = intersection.get_geometry().get_material();
        int nShininess = (int) material.getnShininess();
        double kd = material.getkD();
        double ks = material.getkS();
        // goes through the all lightSources
        for (LightSource lightSource : _scene.get_lights()) {
            Vector l = lightSource.getL(intersection.point);
            if (Math.signum(n.dotProduct(l)) == Math.signum(n.dotProduct(v))) { // if the sign is equel
                double ktr = transparency(lightSource,l,n,intersection);
                if (ktr*k > MIN_CALC_COLOR_K) { // if it's bigger than the minimum k factor
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    Color tmp = lightIntensity.scale(calcSpecular(ks, l, n, v, nShininess, lightIntensity) + calcDiffusive(kd, l, n, lightIntensity));
                    color = color.add(tmp);
                }
            }
        }
        if(level == 1){ // the end f the recursive
            return Color.BLACK;
        }
        double kr = intersection.get_geometry().get_material().get_kR();
        double kkr = k*kr;
        double nv = n.dotProduct(v);
        if(kkr > MIN_CALC_COLOR_K) { // if it's bigger than the minimum k factor
            Ray reflectedRay = constructReflectedRay(intersection.point, nv, inRay, n); // calculates the reflected ray
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if(reflectedPoint != null) { // if it isn't reflected
                color = color.add(calcColor(reflectedPoint, reflectedRay,level-1, kkr).scale(kr));
            }
        }
        double kt = intersection.get_geometry().get_material().get_kT();
        double kkt = k*kt;
        if(kkt > MIN_CALC_COLOR_K) {// if it's bigger than the minimum k factor
            Ray refractedRay = constructRefractedRay(intersection.point, inRay, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay); // calculates the refracted ray
            if(refractedPoint != null) { // if it isn't refracted
                color = color.add(calcColor(refractedPoint, refractedRay,level-1, kkt).scale(kt));
            }
        }
        return color;
    }


    /**
     * Calculates specular light
     * @param ks specular factor
     * @param l vector value
     * @param n normal
     * @param v vector value
     * @param nShininess shininess factor
     * @param lightIntensity intensity factor
     * @return the specular factor
     */
    private double calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity)
    {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double u = (v.scale(-1).dotProduct(r));
        double o = Math.pow(Math.max(u, 0), nShininess) * ks;
        return o;
    }

    /**
     * Calculates diffuse light
     * @param kd diffuse factor
     * @param l vector value
     * @param n normal
     * @param lightIntensity intensity factor
     * @return the diffuse factor
     */
    private double calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity)
    {
        double t = n.dotProduct(l) * kd;
        if (t < 0) {
            t = t * -1;
        }
        return t;
    }

    /**
     * a function that return if the this and the parameter have the same sign
     * @param val double value
     * @return boolean value if both signs are the same
     */
    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * checks if a geometry is shaded
     * @param light lightSource value
     * @param l direction
     * @param n normal
     * @param gp intersection point
     * @return boolean value if shaded or not
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp)
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA); // moves the vector in the direction of the normal
        Point3D point = gp.point.add(delta); // adds the the point the delta
        Ray lightRay = new Ray(point, lightDirection); //builds the ray for the lightSource
        List<GeoPoint> intersections = (_scene.getGeometries()).findIntersections(lightRay); // calculates the intersection points
        if (intersections == null) { // if there aren't any intersection points
            return true;
        }
        double lightDistance = light.getDistance(gp.point); // calculates the distance between the point to the lightSource
        for (GeoPoint gp1 : intersections) { // goes through all the intersection points
            double temp = gp1.point.distance(gp.point) - lightDistance;
            if (Util.alignZero(temp) <= 0 && gp1.get_geometry().get_material().get_kT() != 0) { // if the geometry is unshaded
                return false;
            }
        }
        return true; // if the geometry is shaded
    }

    /**
     * builds a refracted ray
     * @param pointGeo the point which is built through it the refracted ray
     * @param inRay the ray which the pointGeo is on
     * @param n normal
     * @return refracted ray that was built
     */
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n)
    {
        return new Ray(pointGeo, inRay.get_vector(), n);
    }

    /**
     * builds a reflected ray
     * @param pointGeo the point which is built through it the reflected ray
     * @param nv value to move the vector
     * @param inRay the ray which the pointGeo is on
     * @param n normal
     * @return reflected ray that was built
     */
    private Ray constructReflectedRay(Point3D pointGeo, double nv, Ray inRay, Vector n)
    {
        Vector v = inRay.get_vector();
        Vector r = v.add(n.scale(-2 * nv));
        return new Ray(pointGeo, r, n);
    }

    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     * @param ray intersecting the scene
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray)
    {
        // sves the fields for the function
        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.get_point();
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray); // calculates the intersection points
        if (intersections == null) { // if there aren't any intersection points
            return null;
        }
        for (GeoPoint geoPoint : intersections) { // goes through alll the GeoPoints in intersections
            double distance = ray_p0.distance(geoPoint.point);
            if (distance < closestDistance) { // checks if it's closer than the current closest point
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }

    /**
     * calculates the amount of transparency
     * @param ls light source value
     * @param l direction
     * @param n normal
     * @param gp the point which is checked
     * @return the value of how much it's transparent
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint gp)
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n); // calculates the ray from the light source to the intersection point
        List<GeoPoint> intersections = (_scene.getGeometries()).findIntersections(lightRay); // finds all the intersection points
        if (intersections == null) { // if there aren't any intersection points
            return 1.0;
        }
        double lightDistance = ls.getDistance(gp.point); // calculates the distance between the light source to the point
        double ktr = 1.0;
        for (GeoPoint p : intersections) { // goes through all the GeoPoints in intersections
            if (Util.alignZero(p.point.distance(gp.point) - lightDistance) <= 0) {
                ktr *= p.get_geometry().get_material().get_kT(); // calculates how much the point is transparent
                if (ktr < MIN_CALC_COLOR_K){ // if it's smaller than thje minimum value
                    return 0.0;
                }
            }
        }
        return ktr; // returns the value of how much it's transparent
    }

    /**
     * checks if a geometry is occluded
     * @param light lightSource value
     * @param l direction
     * @param n normal
     * @param geopoint the point which is checked
     * @return boolean if occluded or not
     */
    private boolean occluded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        // saves fields for the function
        Point3D geometryPoint = geopoint.getPoint();
        Vector lightDirection = light.getL(geometryPoint);
        lightDirection.scale(-1); // creates a vector that's opposite to the given direction
        Vector epsVector = geopoint.get_geometry().getNormal(geometryPoint);
        // if the normal is in the same direction as the light source - it's multiple by 2, otherwise, it's in the other direction - it's multiple by -2
        epsVector.scale(epsVector.dotProduct(lightDirection) > 0 ? 2 : -2);
        geometryPoint.add(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geopoint.get_geometry() instanceof FlatGeometry) {
            intersections.remove(geopoint);
        }

        for (GeoPoint entry : intersections) { // goes through all the GeoPoints in intersections
            if (entry.get_geometry().get_material().get_kT() == 0) { // if the geometry is occluded
                return true;
            }
        }
        return false; // if the geometry isn't occluded
    }

    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     */
    private class Pixel {
        private long _maxRows = 0; // Ny
        private long _maxCols = 0; // Nx
        private long _pixels = 0; // Total number of pixels: Nx*Ny
        public volatile int row = 0; // Last processed row
        public volatile int col = -1; // Last processed column
        private long _counter = 0; // Total number of pixels processed
        private int _percents = 0; // Percent of pixels processed
        private long _nextCounter = 0; // Next amount of processed pixels for percent progress

        /**
         * The constructor for initializing the main follow up Pixel object
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.printf("\r %02d%%", _percents);
        }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (_print && percents > 0) System.out.printf("\r %02d%%", percents);
            if (percents >= 0) return true;
            if (_print) System.out.printf("\r %02d%%", 100);
            return false;
        }

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_print && _counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                if (_print && _counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }
    }
}
