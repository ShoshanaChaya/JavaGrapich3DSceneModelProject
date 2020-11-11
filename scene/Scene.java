package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Scene is a class representing a scene
 * @author Shoshana Chaya and Yael
*/
public class Scene {
    /**************** fields **************/
    private final String _name;
    private final Geometries _geometries;

    private Color _background;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;
    private List<LightSource> _lights = null;

    /**
     * constructor that gets a scenes name, and creates a new scene with the given name and initializes the Geometries with an empty list
     * @param _sceneName the scenes name
     */
    public Scene(String _sceneName){
        this._name= _sceneName;
        _geometries = new Geometries();
        _lights = new LinkedList<LightSource>();
    }
//**************** getters and setters ****************//
    public void setBackground(Color _background) {
        this._background = _background;
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public double getDistance() {
        return _distance;
    }

    public Color getBackground() {
        return this._background;
    }

    public List<LightSource> get_lights() {
        return _lights;
    }

    /**
     * adds the geometries to the list of geometries
     * @param intersectables Intersectable geometries
     */
    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i:intersectables ) {
            _geometries.add(i);
        }
    }

    /**
     * add the given lights to the _lights list
     * @param light lightSource value
     */
    public void addLights(LightSource ... light) {
        if (_lights == null) { // if there aren't any light sources to add
            _lights = new LinkedList<>();
        }
        for (LightSource l:light) { // goes through all the lightSources in light anf adds it to _lights
            _lights.add(l);
        }
    }
}