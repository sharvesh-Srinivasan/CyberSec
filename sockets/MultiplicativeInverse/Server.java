package sockets.MultiplicativeInverse;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5010)) {
            System.out.println("MultiplicativeInverse Server listening on port 5010...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            int a = in.readInt();
            int m = in.readInt();
            
            String result = MultiplicativeInverse.calculateInverse(a, m);
            out.writeUTF(result);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
