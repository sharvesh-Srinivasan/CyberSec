package sockets.PlayfairCipher;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5009)) {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Enter Key: ");
            String key = sc.nextLine();
            
            System.out.print("Enter Plain Text: ");
            String text = sc.nextLine();
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(key);
            out.writeUTF(text);
            
            String prepared = in.readUTF();
            String encrypted = in.readUTF();
            
            System.out.println("Prepared Text : " + prepared);
            System.out.println("Cipher Text   : " + encrypted);
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
