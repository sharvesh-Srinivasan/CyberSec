package sockets.HillCipher;

import java.io.*;
import java.net.*;

public class Server {
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
            
            String encrypted = HillCipher.encrypt(text, keyMatrix);
            out.writeUTF(encrypted);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
