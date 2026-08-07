package sockets_v2.RowTranspositionCipher;

import java.io.*;
import java.net.*;

public class Server {

    public static String encrypt(String text, int[] key) {
        text = text.toUpperCase().replaceAll("\\s", "");
        int cols = key.length;
        while (text.length() % cols != 0)
            text += "X";
        int rows = text.length() / cols;
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = text.charAt(index++);
            }
        }
        StringBuilder cipher = new StringBuilder();
        for (int k = 1; k <= cols; k++) {
            int col = 0;
            for (int j = 0; j < cols; j++) {
                if (key[j] == k) {
                    col = j;
                    break;
                }
            }
            for (int i = 0; i < rows; i++) {
                cipher.append(matrix[i][col]);
            }
        }
        return cipher.toString();
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5007)) {
            System.out.println("RowTranspositionCipher Server listening on port 5007...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            String text = in.readUTF();
            int n = in.readInt();
            int[] key = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.readInt();
            }
            
            String encrypted = encrypt(text, key);
            out.writeUTF(encrypted);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
