package domain;

public final class ScalingMatrix extends MatrixTransformation {

    private final double deltaX;
    private final double deltaY;
    private final double deltaZ;

    public ScalingMatrix(double deltaX, double deltaY, double deltaZ) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
    }

    public double[][] getMatrix() {
        return new double[][] {
            {deltaX,    0,          0,      0   },
            {0,         deltaY,     0,      0   },
            {0,         0,          deltaZ, 0   },
            {0,         0,          0,      1   }
        };
    }

}
