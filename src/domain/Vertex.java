package domain;

public final class Vertex {

    private int     id;
    private double  x;
    private double  y;
    private double  z;

    public Vertex(int id, double x, double y, double z) {
        this.id = id;
        this.x  = x;
        this.y  = y;
        this.z  = z;
    }

    public double[][] getMatrix() {
        return new double[][] {{x}, {y}, {z}};
    }

    public double[][] getMatrixExtended() {
        return new double[][] {{x}, {y}, {z}, {1}};
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setCoordinate(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public void setMatrix(double[][] matrix) {
        setCoordinate(matrix[0][0], matrix[1][0], matrix[2][0]);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
