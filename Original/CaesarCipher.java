import java.util.Scanner;

public class CaesarCipher {

    // Encryption Method
    public static String caesarEncrypt(int key, String text) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int shift = ((ch - 'A') + key) % 26;
                result.append((char) (shift + 'A'));
            }
        }

        return result.toString();
    }

    // Decryption Method
    public static String caesarDecrypt(int key, String text) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int shift = ((ch - 'A') - key + 26) % 26;
                result.append((char) (shift + 'A'));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the key: ");
        int key = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter the string to encrypt: ");
        String text = sc.nextLine().toUpperCase();

        String encrypted = caesarEncrypt(key, text);
        System.out.println("Encrypted String: " + encrypted);

        String decrypted = caesarDecrypt(key, encrypted);
        System.out.println("Decrypted String: " + decrypted);

        sc.close();
    }
}