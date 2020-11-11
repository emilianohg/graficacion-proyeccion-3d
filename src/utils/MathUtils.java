package utils;

public class MathUtils {
    public static double round(double value, int decimals) {
        double factor = 10 * decimals;
        return Math.ceil(value*factor)/factor;
    }
}
