package utils;

public class MatrixUtils {

    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];

        if (a[0].length == b.length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }

        return c;
    }

}
