package domain;

public final class RotationMatrixZ extends MatrixTransformation {

    protected double angle;

    public RotationMatrixZ(double angle) {
        this.angle = (Math.PI/180) * angle;
    }

    public double[][] getMatrix() {
        return new double[][] {
                {Math.cos(angle),   -Math.sin(angle),   0 },
                {Math.sin(angle),   Math.cos(angle),    0 },
                {0,                 0,                  1 }
        };
    }

}
