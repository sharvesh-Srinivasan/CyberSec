package sockets.HillCipher;

public class HillCipher {
    public static String encrypt(String text, int[][] keyMatrix) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        if (text.length() % 2 != 0) {
            text += "X";
        }
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int p1 = text.charAt(i) - 'A';
            int p2 = text.charAt(i + 1) - 'A';
            int c1 = (keyMatrix[0][0] * p1 + keyMatrix[0][1] * p2) % 26;
            int c2 = (keyMatrix[1][0] * p1 + keyMatrix[1][1] * p2) % 26;
            cipherText.append((char) (c1 + 'A'));
            cipherText.append((char) (c2 + 'A'));
        }
        return cipherText.toString();
    }
}
