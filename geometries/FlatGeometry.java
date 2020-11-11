package geometries;

import primitives.Color;
import primitives.Material;

/**
 * Flat Geometry is a Marker abstract class extending Geometry
 * to differentiate it from RadialGeometry
 * we did not declare it as an interface
 *  @author Shoshana Chaya and Yael
 */
public abstract class FlatGeometry extends Geometry {
    /**
     * Associated plane in which the flat geometry lays
     */
    protected Plane _plane;

    /**
     * parameter constructor
     * @param _emission color value
     * @param _material material value
     */
    public FlatGeometry(Color _emission, Material _material) {
        super(_emission, _material);
    }

    /**
     * parameter constructor
     * @param _emission color value
     */
    public FlatGeometry(Color _emission) {
        super(_emission);
    }

    /**
     * default constructor
     */
    public FlatGeometry() {
        super();
    }
}