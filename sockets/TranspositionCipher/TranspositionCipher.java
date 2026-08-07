package sockets.TranspositionCipher;

public class TranspositionCipher {
    public static String encrypt(String text, int key) {
        text = text.toUpperCase().replaceAll("\\s", "");
        while (text.length() % key != 0) {
            text += "X";
        }
        int rows = text.length() / key;
        char[][] matrix = new char[rows][key];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                matrix[i][j] = text.charAt(index++);
            }
        }
        StringBuilder cipher = new StringBuilder();
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                cipher.append(matrix[i][j]);
            }
        }
        return cipher.toString();
    }

    public static String decrypt(String cipher, int key) {
        int rows = cipher.length() / key;
        char[][] matrix = new char[rows][key];
        int index = 0;
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                matrix[i][j] = cipher.charAt(index++);
            }
        }
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                plain.append(matrix[i][j]);
            }
        }
        return plain.toString();
    }
}
