package primitives;

/**
 * Class Material is representing a material
 * @author Shoshana Chaya and Yael
 */
public class Material {
    /*********** fields *************/
    private double _kD;
    private double _kS;
    private int _nShininess;

    private double _kT;
    private double _kR;

    /**
     * constructor that gets the kd, ks and shininess
     * @param _kD double value
     * @param _kS double value
     * @param _nShininess int value
     */
    public Material(double _kD, double _kS, int _nShininess) {
       this(_kD, _kS, _nShininess, 0, 0);
    }

    /**
     * parameter constructor
     * @param _kD double value
     * @param _kS double value
     * @param _nShininess int value
     * @param _kT double value
     * @param _kR double value
     */
    public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
        this._kT = _kT;
        this._kR = _kR;
    }
/************ getters *****************/
    public double getkD() {
        return _kD;
    }

    public double getkS() {
        return _kS;
    }

    public int getnShininess() {
        return _nShininess;
    }
    public double get_kT() {
        return _kT;
    }

    public double get_kR() {
        return _kR;
    }
}