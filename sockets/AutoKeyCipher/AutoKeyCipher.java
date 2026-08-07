package sockets.AutoKeyCipher;

public class AutoKeyCipher {
    public static String encrypt(String text, String key) {
        StringBuilder cipher = new StringBuilder();
        String fullKey = key + text;
        for (int i = 0; i < text.length(); i++) {
            int p = text.charAt(i) - 'A';
            int k = fullKey.charAt(i) - 'A';
            int c = (p + k) % 26;
            cipher.append((char) (c + 'A'));
        }
        return cipher.toString();
    }

    public static String decrypt(String cipherText, String key) {
        StringBuilder plain = new StringBuilder();
        StringBuilder fullKey = new StringBuilder(key);
        for (int i = 0; i < cipherText.length(); i++) {
            int c = cipherText.charAt(i) - 'A';
            int k = fullKey.charAt(i) - 'A';
            int p = (c - k + 26) % 26;
            char plainChar = (char) (p + 'A');
            plain.append(plainChar);
            fullKey.append(plainChar);
        }
        return plain.toString();
    }
}
