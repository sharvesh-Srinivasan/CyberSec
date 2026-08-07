package sockets.MultiplicativeInverse;

public class MultiplicativeInverse {

    static int x, y;

    // Extended Euclidean Algorithm
    public static int gcdExtended(int a, int b) {

        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }

        int gcd = gcdExtended(b, a % b);

        int x1 = y;
        int y1 = x - (a / b) * y;

        x = x1;
        y = y1;

        return gcd;
    }

    public static String calculateInverse(int a, int m) {
        int gcd = gcdExtended(a, m);

        if (gcd != 1) {
            return "Multiplicative Inverse does not exist.";
        } else {
            int inverse = (x % m + m) % m;
            return "Multiplicative Inverse = " + inverse;
        }
    }
}
