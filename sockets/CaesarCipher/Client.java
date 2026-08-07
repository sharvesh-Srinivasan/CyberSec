package sockets.CaesarCipher;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5001)) {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Enter the key: ");
            int key = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter the string to encrypt: ");
            String text = sc.nextLine().toUpperCase();
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeInt(key);
            out.writeUTF(text);
            
            String encrypted = in.readUTF();
            String decrypted = in.readUTF();
            
            System.out.println("Encrypted String received from server: " + encrypted);
            System.out.println("Decrypted String received from server: " + decrypted);
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
