package sockets.AffineCipher;

import java.io.*;
import java.net.*;

public class Server {
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
            
            String encrypted = AffineCipher.encrypt(a, b, text);
            String decrypted = AffineCipher.decrypt(a, b, encrypted);
            
            out.writeUTF(encrypted);
            out.writeUTF(decrypted != null ? decrypted : "Decryption failed: 'a' is not coprime with 26.");
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
