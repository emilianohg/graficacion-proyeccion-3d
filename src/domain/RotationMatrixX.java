package domain;

public final class RotationMatrixX extends MatrixTransformation {

    protected double angle;

    public RotationMatrixX(double angle) {
        this.angle = (Math.PI/180) * angle;
    }

    public double[][] getMatrix() {
        return new double[][] {
            {1,     0,                  0               },
            {0,     Math.cos(angle),    -Math.sin(angle)},
            {0,     Math.sin(angle),    Math.cos(angle) }
        };
    }

}
