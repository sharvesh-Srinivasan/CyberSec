package sockets.ColumnarCipher;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5006)) {
            System.out.println("ColumnarCipher Server listening on port 5006...");
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
            
            String encrypted = ColumnarCipher.encrypt(text, key);
            out.writeUTF(encrypted);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
