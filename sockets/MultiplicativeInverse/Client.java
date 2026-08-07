package sockets.MultiplicativeInverse;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5010)) {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Enter the number: ");
            int a = sc.nextInt();
            
            System.out.print("Enter the modulus: ");
            int m = sc.nextInt();
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeInt(a);
            out.writeInt(m);
            
            String result = in.readUTF();
            System.out.println("Server returned: " + result);
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
