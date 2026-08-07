package sockets.PolyAlphhabeticCipher;

public class PolyAlphhabeticCipher {
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char p = text.charAt(i);
            char k = key.charAt(i % key.length());
            int cipher = ((p - 'A') + (k - 'A')) % 26;
            result.append((char) (cipher + 'A'));
        }
        return result.toString();
    }

    public static String decrypt(String cipherText, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            char k = key.charAt(i % key.length());
            int plain = ((c - 'A') - (k - 'A') + 26) % 26;
            result.append((char) (plain + 'A'));
        }
        return result.toString();
    }
}
