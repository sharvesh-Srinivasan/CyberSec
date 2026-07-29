import java.util.Scanner;

public class MultiplicativeInverse {

    static int x, y;

    // Extended Euclidean Algorithm
    static int gcdExtended(int a, int b) {

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number: ");
        int a = sc.nextInt();

        System.out.print("Enter the modulus: ");
        int m = sc.nextInt();

        int gcd = gcdExtended(a, m);

        if (gcd != 1) {
            System.out.println("Multiplicative Inverse does not exist.");
        } else {
            int inverse = (x % m + m) % m;
            System.out.println("Multiplicative Inverse = " + inverse);
        }

        sc.close();
    }
}