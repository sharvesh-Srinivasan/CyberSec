package sockets.CaesarCipher;

public class CaesarCipher {
    public static String encrypt(int key, String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int shift = ((ch - 'A') + key) % 26;
                result.append((char) (shift + 'A'));
            }
        }
        return result.toString();
    }

    public static String decrypt(int key, String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int shift = ((ch - 'A') - key + 26) % 26;
                result.append((char) (shift + 'A'));
            }
        }
        return result.toString();
    }
}
