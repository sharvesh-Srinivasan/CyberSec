package sockets.PlayfairCipher;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5009)) {
            System.out.println("PlayfairCipher Server listening on port 5009...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            String key = in.readUTF();
            String text = in.readUTF();
            
            String result = PlayfairCipher.encrypt(text, key);
            String[] parts = result.split(";");
            
            out.writeUTF(parts[0]); // prepared text
            out.writeUTF(parts[1]); // encrypted text
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
