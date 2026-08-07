package sockets_v2.AffineCipher;

import java.io.*;
import java.net.*;

public class Server {

    static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    public static String encrypt(int a, int b, String text) {
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                int val = ch - 'A';
                int enc = (a * val + b) % 26;
                cipher.append((char) (enc + 'A'));
            } else {
                cipher.append(ch);
            }
        }
        return cipher.toString();
    }

    public static String decrypt(int a, int b, String cipherText) {
        StringBuilder plain = new StringBuilder();
        int a_inv = modInverse(a, 26);
        if (a_inv == -1) {
            return null; // Mod inverse doesn't exist
        }
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            if (Character.isLetter(ch)) {
                int val = ch - 'A';
                int dec = (a_inv * (val - b + 26)) % 26;
                plain.append((char) (dec + 'A'));
            } else {
                plain.append(ch);
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5002)) {
            System.out.println("AffineCipher Server listening on port 5002...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            int a = in.readInt();
            int b = in.readInt();
            String text = in.readUTF();
            
            String encrypted = encrypt(a, b, text);
            String decrypted = decrypt(a, b, encrypted);
            
            out.writeUTF(encrypted);
            out.writeUTF(decrypted != null ? decrypted : "Decryption failed: 'a' is not coprime with 26.");
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
