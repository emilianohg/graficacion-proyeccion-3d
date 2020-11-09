package domain;

public class Transformation {
    private double scaleX;
    private double scaleY;
    private double scaleZ;
    private double rotateX;
    private double rotateY;
    private double rotateZ;
    private double translateX;
    private double translateY;
    private double translateZ;

    public Transformation(
        double scaleX,
        double scaleY,
        double scaleZ,
        double rotateX,
        double rotateY,
        double rotateZ,
        double translateX,
        double translateY,
        double translateZ
    ) {
        this.scaleX     = scaleX;
        this.scaleY     = scaleY;
        this.scaleZ     = scaleZ;
        this.rotateX    = rotateX;
        this.rotateY    = rotateY;
        this.rotateZ    = rotateZ;
        this.translateX = translateX;
        this.translateY = translateY;
        this.translateZ = translateZ;
    }

    public Transformation() {
        this(1, 1, 1, 0, 0, 0, 0, 0, 0);
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public double getScaleZ() {
        return scaleZ;
    }

    public void setScaleZ(double scaleZ) {
        this.scaleZ = scaleZ;
    }

    public double getRotateX() {
        return rotateX;
    }

    public void setRotateX(double rotateX) {
        this.rotateX = rotateX;
    }

    public double getRotateY() {
        return rotateY;
    }

    public void setRotateY(double rotateY) {
        this.rotateY = rotateY;
    }

    public double getRotateZ() {
        return rotateZ;
    }

    public void setRotateZ(double rotateZ) {
        this.rotateZ = rotateZ;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX=translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY=translateY;
    }

    public double getTranslateZ() {
        return translateZ;
    }

    public void setTranslateZ(double translateZ) {
        this.translateZ=translateZ;
    }
}
