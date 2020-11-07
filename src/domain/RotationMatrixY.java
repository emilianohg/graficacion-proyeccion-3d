package domain;

public final class RotationMatrixY extends MatrixTransformation {

    protected double angle;

    public RotationMatrixY(double angle) {
        this.angle = (Math.PI/180) * angle;
    }

    public double[][] getMatrix() {
        return new double[][] {
            {Math.cos(angle),   0,   Math.sin(angle) },
            {0,                 1,   0               },
            {-Math.sin(angle),  0,   Math.cos(angle) }
        };
    }

}
