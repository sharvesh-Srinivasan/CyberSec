import java.util.Scanner;

public class AutoKeyCipher {

    // Encryption
    public static String encrypt(String plain, String key) {

        plain = plain.toUpperCase();
        key = key.toUpperCase();

        // Generate full key
        String fullKey = key;

        while (fullKey.length() < plain.length()) {
            fullKey += plain.charAt(fullKey.length() - key.length());
        }

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < plain.length(); i++) {

            int p = plain.charAt(i) - 'A';
            int k = fullKey.charAt(i) - 'A';

            int c = (p + k) % 26;

            cipher.append((char)(c + 'A'));
        }

        return cipher.toString();
    }

    // Decryption
    public static String decrypt(String cipher, String key) {

        cipher = cipher.toUpperCase();
        key = key.toUpperCase();

        StringBuilder plain = new StringBuilder();

        String currentKey = key;

        for (int i = 0; i < cipher.length(); i++) {

            char keyChar;

            if (i < key.length())
                keyChar = currentKey.charAt(i);
            else
                keyChar = plain.charAt(i - key.length());

            int c = cipher.charAt(i) - 'A';
            int k = keyChar - 'A';

            int p = (c - k + 26) % 26;

            plain.append((char)(p + 'A'));
        }

        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String plain = sc.nextLine().toUpperCase();

        System.out.print("Enter Keyword: ");
        String key = sc.nextLine().toUpperCase();

        String cipher = encrypt(plain, key);

        System.out.println("Encrypted Text : " + cipher);

        String decrypted = decrypt(cipher, key);

        System.out.println("Decrypted Text : " + decrypted);

        sc.close();
    }
}