package sockets_v2.CaesarCipher;

import java.io.*;
import java.net.*;

public class Server {

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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            System.out.println("CaesarCipher Server listening on port 5001...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            int key = in.readInt();
            String text = in.readUTF();
            
            String encrypted = encrypt(key, text);
            String decrypted = decrypt(key, encrypted);
            
            out.writeUTF(encrypted);
            out.writeUTF(decrypted);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
