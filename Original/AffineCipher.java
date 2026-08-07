import java.util.Scanner;

public class AffineCipher {

    // Function to find modular inverse of a mod 26
    public static int modInverse(int a, int m) {
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1) {
                return i;
            }
        }
        return -1; // Inverse doesn't exist
    }

    // Encryption
    public static String affineEncrypt(int a, int b, String text) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            int convert = ch - 'A';
            int res = (a * convert + b) % 26;
            result.append((char) (res + 'A'));
        }

        return result.toString();
    }

    // Decryption
    public static String affineDecrypt(int a, int b, String text) {
        StringBuilder result = new StringBuilder();

        int aInv = modInverse(a, 26);

        if (aInv == -1) {
            return "Modular inverse does not exist. Invalid key!";
        }

        for (char ch : text.toCharArray()) {
            int convert = ch - 'A';
            int res = (aInv * (convert - b + 26)) % 26;
            result.append((char) (res + 'A'));
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the value of a (must be coprime with 26): ");
        int a = sc.nextInt();

        System.out.print("Enter the value of b: ");
        int b = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter the string to encrypt (uppercase letters only): ");
        String text = sc.nextLine().toUpperCase();

        String encrypted = affineEncrypt(a, b, text);
        System.out.println("Encrypted String: " + encrypted);

        String decrypted = affineDecrypt(a, b, encrypted);
        System.out.println("Decrypted String: " + decrypted);

        sc.close();
    }
}