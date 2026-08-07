package sockets_v2.AffineCipher;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5002)) {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Enter the value of a (must be coprime with 26): ");
            int a = sc.nextInt();
            System.out.print("Enter the value of b: ");
            int b = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Enter the string to encrypt: ");
            String text = sc.nextLine().toUpperCase();
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeInt(a);
            out.writeInt(b);
            out.writeUTF(text);
            
            String encrypted = in.readUTF();
            String decrypted = in.readUTF();
            
            System.out.println("Encrypted String: " + encrypted);
            System.out.println("Decrypted String: " + decrypted);
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
