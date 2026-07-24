import java.util.*;

public class PlayfairCipher {

    static char[][] matrix = new char[5][5];

    // Create 5x5 key matrix
    static void generateMatrix(String key) {
        boolean[] visited = new boolean[26];
        key = key.toUpperCase().replace("J", "I");

        StringBuilder sb = new StringBuilder();

        for (char c : key.toCharArray()) {
            if (c >= 'A' && c <= 'Z' && !visited[c - 'A']) {
                visited[c - 'A'] = true;
                sb.append(c);
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!visited[c - 'A']) {
                visited[c - 'A'] = true;
                sb.append(c);
            }
        }

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = sb.charAt(k++);
            }
        }
    }

    // Print matrix
    static void printMatrix() {
        System.out.println("\nPlayfair Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Find position of character
    static int[] findPosition(char ch) {
        if (ch == 'J')
            ch = 'I';

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    // Prepare plaintext
    static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));

            if (i + 1 < text.length()) {
                if (text.charAt(i) == text.charAt(i + 1))
                    sb.append('X');
            }
        }

        if (sb.length() % 2 != 0)
            sb.append('X');

        return sb.toString();
    }

    // Encrypt
    static String encrypt(String text) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {

            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] p1 = findPosition(a);
            int[] p2 = findPosition(b);

            // Same Row
            if (p1[0] == p2[0]) {
                cipher.append(matrix[p1[0]][(p1[1] + 1) % 5]);
                cipher.append(matrix[p2[0]][(p2[1] + 1) % 5]);
            }

            // Same Column
            else if (p1[1] == p2[1]) {
                cipher.append(matrix[(p1[0] + 1) % 5][p1[1]]);
                cipher.append(matrix[(p2[0] + 1) % 5][p2[1]]);
            }

            // Rectangle
            else {
                cipher.append(matrix[p1[0]][p2[1]]);
                cipher.append(matrix[p2[0]][p1[1]]);
            }
        }

        return cipher.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        generateMatrix(key);
        printMatrix();

        System.out.print("\nEnter Plain Text: ");
        String text = sc.nextLine();

        text = prepareText(text);

        System.out.println("Prepared Text : " + text);

        String cipher = encrypt(text);

        System.out.println("Cipher Text   : " + cipher);

        sc.close();
    }
}