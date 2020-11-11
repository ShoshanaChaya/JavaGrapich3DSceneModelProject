package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.Random;

import static primitives.Util.isZero;

/**
 * Class Camera is the basic class representing a camera
 * @author Shoshana Chaya and Yael
 */
public class Camera {
    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;

    /**
     * parameter constructor
     * @param _p0 point3D value
     * @param _vTo vector value
     * @param _vUp vector value
     */
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {
        //if the the vectors are not orthogonal, throw exception.
        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");
        this._p0 =  new Point3D(_p0);
        this._vTo = _vTo.normalized();
        this._vUp = _vUp.normalized();
        // calculates vector vRight
        _vRight = this._vTo.crossProduct(this._vUp).normalize();
    }

    /**
     * constructs ray through pixel
     * @param nX number of pixels on the X axis
     * @param nY number of pixels on the Y axis
     * @param j the given line
     * @param i the given column
     * @param screenDistance distance between the camera and the view plane
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @return returns a ray through the given pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }
        //creating a point for the middle of the view plane
        Point3D Pc = _p0.add(_vTo.scale(screenDistance));
        double Ry = screenHeight/nY;//the height of each pixel
        double Rx = screenWidth/nX;//the width of each pixel
        //the value on the yi axis we moved to
        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        //the value on the xi axis we moved to
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;
        //if the xj isn't a zero we will move the Pij point in the direction
        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        //if the yi isn't a zero we will move the Pij point in the direction
        if (! isZero(yi)) {
            Pij = Pij.subtract(_vUp.scale(yi));
        }
        // a vector to build the ray
        Vector Vij = (Pij.subtract(_p0)).normalize();
        // return the ray
        return new Ray(_p0,Vij);
    }


    /**
     * builds a ray through a pixel
     * @param nX number of pixels on the X axis
     * @param nY number of pixels on the Y axis
     * @param j the given line
     * @param i the given column
     * @param screenDistance distance between the camera and the view plane
     * @param screenWidth  the width of the screen
     * @param screenHeight the height of the screen
     * @return list of rays who create a ray beam
     */
    public LinkedList<Ray> constructRayBeamThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight, int level)
    {
        if (isZero(screenDistance))
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }
        //creating a point for the middle of the view plane
        Point3D Pc = _p0.add(_vTo.scale(screenDistance));
        double Ry = screenHeight/nY;//the height of each pixel
        double Rx = screenWidth/nX;//the width of each pixel

        //the value on the y axis we moved to
        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        //the value on the x axis we moved to
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;
        //if the xj isn't a zero we will move the Pij point in the direction
        if (! isZero(xj))
        {
            Pij = Pij.add(_vRight.scale(xj));
        }
        //if the yi isn't a zero we will move the Pij point in the direction
        if (! isZero(yi))
        {
            Pij = Pij.add(_vUp.scale(-yi));
        }
        // a vector to build the ray
        Vector Vij = Pij.subtract(_p0);
        Ray ray_center = new Ray(_p0, Vij.normalized());

        LinkedList<Ray> rays = new LinkedList<>();
        rays.add(ray_center);
        // finds the extreme values for the random numbers
        double rang_miny = (i - nY / 2d) * Ry;
        double rang_maxy = (i - nY / 2d) * Ry + Ry;
        double rang_minx = (j - nX / 2d) * Rx;
        double rang_max = (j - nX / 2d) * Rx + Rx;
        Random rand = new Random();
        // builds the level amount of rays
        for(int k = 0; k < level; k++)
        {
            // sets random numbers within the specified range for x and y
            double x = rang_minx + (rang_max - rang_minx) * rand.nextDouble();
            double y = rang_miny + (rang_maxy - rang_miny) * rand.nextDouble();
            Pij = Pc;
            //if the x isn't a zero we will move the Pij point in the direction
            if (!isZero(x))
            {
                Pij = Pij.add(_vRight.scale(x));
            }
            //if the y isn't a zero we will move the Pij point in the direction
            if (!isZero(y))
            {
                Pij = Pij.add(_vUp.scale(-y));
            }
            // builds the ray
            Ray ray = new Ray(_p0, Vij);
            // adds the ray
            rays.add(ray);
        }
        // returns the list of rays
        return rays;
    }

/************************** getter *************************/
    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    public Vector get_vTo() {
        return new Vector(_vTo);
    }

    public Vector get_vUp() {
        return new Vector(_vUp);
    }

    public Vector get_vRight() {
        return new Vector(_vRight);
    }


    /**
     * calculates the point after we moved it
     * @param pc the point that is the middle of the pixel
     * @param yi the place we need to move the y value
     * @param xj the place we need to move the x value
     * @param offsetX the offset for the x
     * @param offsetY the offset for the y
     * @return point3D value in the new place
     */
    //Thanks to Naama and Kayla
    private Point3D newPoint(Point3D pc, double yi, double xj, double offsetX, double offsetY) {
        Point3D point = pc;//the point the is the moddle of the pixel
        //the amount we are moving the point on the x axis
        double x = xj + offsetX;
        //the amount we are moving the point on the y axis
        double y = -yi - offsetY;
        //if the x isn't a zero we will move the Pij point in the direction
        if (!isZero(x)) {
            point = point.add(get_vRight().scale(x));
        }
        //if the y isn't a zero we will move the Pij point in the direction
        if (!isZero(y)) {
            point = point.add(get_vUp().scale(y));
        }
        return point;
    }

    /**
     * constructs adaptive ray beam through a given pixel
     * @param nX number of pixels on the x axis
     * @param nY number of pixels on the y axis
     * @param j the given line
     * @param i the given column
     * @param screenDistance distance between the camera and the view plane
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param cornerTopX the double value for the top value of x
     * @param cornerTopY the double value for the top value of y
     * @param cornerBottomX the double value for the bottom value of x
     * @param cornerBottomY the double value for the bottom value of y
     * @return list of rays
     */
    public LinkedList<Ray> constructAdaptiveRayBeamThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight,
                                                                double cornerTopX, double cornerTopY,double cornerBottomX,double cornerBottomY) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        LinkedList<Ray> rays = new LinkedList<>();
        //creating a point for the middle of the view plane
        Point3D Pc = get_p0().add(get_vTo().scale(screenDistance));

        double Ry = screenHeight / nY;//the height of each pixel
        double Rx = screenWidth / nX;//the width of each pixel

        //the value on the y axis we moved to
        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        //the value on the x axis we moved to
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;
        //if the xj isn't a zero we will move the Pij point in the direction
        if (!isZero(xj)) {
            Pij = Pij.add(get_vRight().scale(xj));
        }
        //if the yi isn't a zero we will move the Pij point in the direction
        if (!isZero(yi)) {
            Pij = Pij.add(get_vUp().scale(-1 * yi));
        }
        //calculates the center of the pixel by doing a mean between the top and bottom values of x and y
        Point3D Center =  newPoint( Pc, yi, xj,(cornerTopX + cornerBottomX)/2,(cornerTopY + cornerBottomY)/2);
        //calculating the values for the topRightCorner, topLeftCorner, bottomRightCorner, bottomLeftCorner:
        Point3D TL = newPoint( Pc, yi, xj,cornerTopX,cornerTopY);
        Point3D TR = newPoint( Pc, yi, xj,cornerTopX,cornerBottomY);
        Point3D BR = newPoint( Pc, yi, xj,cornerBottomX,cornerBottomY);
        Point3D BL = newPoint( Pc, yi, xj,cornerBottomX,cornerTopY);
        //adding the rays to the list we will return, the rays will be those from the center of the camera to the topLeft, topRight, bottomLeft, bottomRight
        rays.add(new Ray(get_p0(), new Vector(Center.subtract(get_p0()))));
        rays.add(new Ray(get_p0(), new Vector(TL.subtract(get_p0()))));
        rays.add(new Ray(get_p0(), new Vector(TR.subtract(get_p0()))));
        rays.add(new Ray(get_p0(), new Vector(BL.subtract(get_p0()))));
        rays.add(new Ray(get_p0(), new Vector(BR.subtract(get_p0()))));
        return rays;

    }
}