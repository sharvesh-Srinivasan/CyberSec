import java.util.Scanner;

public class AffineBruteForce {

    // Find modular inverse
    public static int modInverse(int a, int m) {
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1)
                return i;
        }
        return -1;
    }

    // Decrypt
    public static String decrypt(String cipher, int a, int b) {

        StringBuilder plain = new StringBuilder();
        int aInv = modInverse(a, 26);

        if (aInv == -1)
            return "";

        for (char ch : cipher.toCharArray()) {
            if (Character.isLetter(ch)) {
                int x = ch - 'A';
                int p = (aInv * (x - b + 26)) % 26;
                plain.append((char)(p + 'A'));
            }
        }

        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Cipher Text: ");
        String cipher = sc.nextLine().toUpperCase();

        int[] validA = {1,3,5,7,9,11,15,17,19,21,23,25};

        System.out.println("\nPossible Plain Texts:\n");

        for (int a : validA) {
            for (int b = 0; b < 26; b++) {

                String plain = decrypt(cipher, a, b);

                System.out.println("a = " + a +
                                   "  b = " + b +
                                   "  --> " + plain);
            }
        }

        sc.close();
    }
}