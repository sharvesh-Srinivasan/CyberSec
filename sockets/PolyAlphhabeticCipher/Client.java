package sockets.PolyAlphhabeticCipher;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5004)) {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Enter Plain Text: ");
            String text = sc.nextLine().toUpperCase();
            System.out.print("Enter Key: ");
            String key = sc.nextLine().toUpperCase();
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(text);
            out.writeUTF(key);
            
            String encrypted = in.readUTF();
            String decrypted = in.readUTF();
            
            System.out.println("Encrypted Text: " + encrypted);
            System.out.println("Decrypted Text: " + decrypted);
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
