package domain;

public final class TranslationMatrix extends MatrixTransformation {

    private final double deltaX;
    private final double deltaY;
    private final double deltaZ;

    public TranslationMatrix(double deltaX, double deltaY, double deltaZ) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
    }

    public double[][] getMatrix() {
        return new double[][] {
            {1,     0,      0,    deltaX  },
            {0,     1,      0,    deltaY  },
            {0,     0,      1,    deltaZ  },
            {0,     0,      0,    1       }
        };
    }

}
