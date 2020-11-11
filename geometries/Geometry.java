package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;

/**
 * Abstract class Geometry is the basic class representing a geometry
 * @author Shoshana Chaya and Yael
 */
public abstract class Geometry implements Intersectable
{
    /************** fields *******************/
    protected Color _emission;
    protected Material _material;

    /**
     * constructor that gets a color and material
     * @param _emission color value
     * @param _material material value
     */
    public Geometry(Color _emission, Material _material) {
        this._emission = _emission;
        this._material = _material;
    }

    /**
     * constructor that gets a color
     * @param _emission color value
     */
    public Geometry(Color _emission) {
        this(_emission, new Material(0d, 0d, 0));
    }

    /**
     * default constructor
     */
    public Geometry() {
        this(Color.BLACK);
    }

    /************* getters *****************/
    public Color get_emission() {
        return _emission;
    }

    public Material get_material() {
        return _material;
    }

    /**
     * calculates the normal to a given point
     * @param point point3D value
     * @return normal
     */
    public abstract primitives.Vector getNormal (Point3D point);
}
