package sockets_v2.PolyAlphhabeticCipher;

import java.io.*;
import java.net.*;

public class Server {

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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5004)) {
            System.out.println("PolyAlphhabeticCipher Server listening on port 5004...");
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
