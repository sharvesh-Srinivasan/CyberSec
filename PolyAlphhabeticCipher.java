import java.util.Scanner;

public class PolyAlphhabeticCipher {

    // Encryption
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();

        text = text.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char p = text.charAt(i);
            char k = key.charAt(i % key.length());

            int cipher = ((p - 'A') + (k - 'A')) % 26;
            result.append((char) (cipher + 'A'));
        }

        return result.toString();
    }

    // Decryption
    public static String decrypt(String cipherText, String key) {
        StringBuilder result = new StringBuilder();

        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();

        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            char k = key.charAt(i % key.length());

            int plain = ((c - 'A') - (k - 'A') + 26) % 26;
            result.append((char) (plain + 'A'));
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine().toUpperCase();

        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}