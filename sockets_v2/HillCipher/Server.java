package sockets_v2.HillCipher;

import java.io.*;
import java.net.*;

public class Server {

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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5008)) {
            System.out.println("HillCipher Server listening on port 5008...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            int[][] keyMatrix = new int[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    keyMatrix[i][j] = in.readInt();
                }
            }
            String text = in.readUTF();
            
            String encrypted = encrypt(text, keyMatrix);
            out.writeUTF(encrypted);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
