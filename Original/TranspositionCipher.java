import java.util.*;

public class TranspositionCipher {

    // Encryption
    public static String encrypt(String text, int key) {

        text = text.toUpperCase().replaceAll("\\s", "");

        while (text.length() % key != 0) {
            text += "X";
        }

        int rows = text.length() / key;

        char[][] matrix = new char[rows][key];

        int index = 0;

        // Fill row-wise
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                matrix[i][j] = text.charAt(index++);
            }
        }

        StringBuilder cipher = new StringBuilder();

        // Read column-wise
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                cipher.append(matrix[i][j]);
            }
        }

        return cipher.toString();
    }

    // Decryption
    public static String decrypt(String cipher, int key) {

        int rows = cipher.length() / key;

        char[][] matrix = new char[rows][key];

        int index = 0;

        // Fill column-wise
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                matrix[i][j] = cipher.charAt(index++);
            }
        }

        StringBuilder plain = new StringBuilder();

        // Read row-wise
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                plain.append(matrix[i][j]);
            }
        }

        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Key (Columns): ");
        int key = sc.nextInt();

        String cipher = encrypt(text, key);

        System.out.println("Encrypted Text : " + cipher);

        String plain = decrypt(cipher, key);

        System.out.println("Decrypted Text : " + plain);

        sc.close();
    }
}