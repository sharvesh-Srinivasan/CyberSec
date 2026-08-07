package sockets_v2.MultiplicativeInverse;

import java.io.*;
import java.net.*;

public class Server {

    static int x, y;

    // Extended Euclidean Algorithm
    public static int gcdExtended(int a, int b) {

        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }

        int gcd = gcdExtended(b, a % b);

        int x1 = y;
        int y1 = x - (a / b) * y;

        x = x1;
        y = y1;

        return gcd;
    }

    public static String calculateInverse(int a, int m) {
        int gcd = gcdExtended(a, m);

        if (gcd != 1) {
            return "Multiplicative Inverse does not exist.";
        } else {
            int inverse = (x % m + m) % m;
            return "Multiplicative Inverse = " + inverse;
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5010)) {
            System.out.println("MultiplicativeInverse Server listening on port 5010...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            int a = in.readInt();
            int m = in.readInt();
            
            String result = calculateInverse(a, m);
            out.writeUTF(result);
            
            socket.close();
            System.out.println("Server connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
