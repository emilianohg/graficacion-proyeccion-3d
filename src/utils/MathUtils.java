package utils;

import java.math.BigInteger;

public class MathUtils {
    public static double round(double value, int decimals) {
        double factor = 10 * decimals;
        return Math.ceil(value*factor)/factor;
    }

    public static String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes()));
    }
}
