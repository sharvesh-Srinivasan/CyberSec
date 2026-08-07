package sockets_v2.AutoKeyCipher;

import java.io.*;
import java.net.*;

public class Server {

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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5003)) {
            System.out.println("AutoKeyCipher Server listening on port 5003...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            String text = in.readUTF();
            String key = in.readUTF();
            
            String encrypted = encrypt(text, key);
            String decrypted = decrypt(encrypted, key);
            
            out.writeUTF(encrypted);
            out.writeUTF(decrypted);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
