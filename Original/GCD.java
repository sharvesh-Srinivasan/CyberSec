import java.util.Scanner;

public class GCD {

    // Recursive method to find GCD
    public static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
    public static boolean Checkcoprime(int a, int b){
        if (gcd(a, b) == 1) {
            return true;
        } else {
            return false;
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        int a = sc.nextInt();

        System.out.print("Enter the second number: ");
        int b = sc.nextInt();

        System.out.println("GCD: " + gcd(a, b));
        
        if (Checkcoprime(a, b)) {
            System.out.println(a + " and " + b + " are coprime.");
        } else {
            System.out.println(a + " and " + b + " are not coprime.");
        }
        sc.close();
    }
}