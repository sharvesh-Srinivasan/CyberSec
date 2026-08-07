package sockets.CaesarCipher;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            System.out.println("CaesarCipher Server listening on port 5001...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            int key = in.readInt();
            String text = in.readUTF();
            
            String encrypted = CaesarCipher.encrypt(key, text);
            String decrypted = CaesarCipher.decrypt(key, encrypted);
            
            out.writeUTF(encrypted);
            out.writeUTF(decrypted);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
