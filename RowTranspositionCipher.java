import java.util.*;

public class RowTranspositionCipher {

    // Encryption
    public static String encrypt(String text, int[] key) {

        text = text.toUpperCase().replaceAll("\\s", "");

        int cols = key.length;

        while (text.length() % cols != 0)
            text += "X";

        int rows = text.length() / cols;

        char[][] matrix = new char[rows][cols];

        int index = 0;

        // Fill matrix row-wise
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = text.charAt(index++);
            }
        }

        StringBuilder cipher = new StringBuilder();

        // Read columns according to key
        for (int k = 1; k <= cols; k++) {
            int col = 0;

            for (int j = 0; j < cols; j++) {
                if (key[j] == k) {
                    col = j;
                    break;
                }
            }

            for (int i = 0; i < rows; i++) {
                cipher.append(matrix[i][col]);
            }
        }

        return cipher.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Number of Columns: ");
        int n = sc.nextInt();

        int[] key = new int[n];

        System.out.println("Enter Key:");

        for (int i = 0; i < n; i++) {
            key[i] = sc.nextInt();
        }

        String cipher = encrypt(text, key);

        System.out.println("Cipher Text: " + cipher);

        sc.close();
    }
}