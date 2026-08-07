package sockets_v2.TranspositionCipher;

import java.io.*;
import java.net.*;

public class Server {

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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5005)) {
            System.out.println("TranspositionCipher Server listening on port 5005...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            String text = in.readUTF();
            int key = in.readInt();
            
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
