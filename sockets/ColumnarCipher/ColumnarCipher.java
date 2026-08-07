package sockets.ColumnarCipher;

public class ColumnarCipher {
    public static String encrypt(String text, int[] key) {
        text = text.toUpperCase().replaceAll("\\s", "");
        int cols = key.length;
        while (text.length() % cols != 0)
            text += "X";
        int rows = text.length() / cols;
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = text.charAt(index++);
            }
        }
        StringBuilder cipher = new StringBuilder();
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
}
