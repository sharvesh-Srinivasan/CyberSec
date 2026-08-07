package sockets.RowTranspositionCipher;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5007)) {
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Enter Plain Text: ");
            String text = sc.nextLine();
            
            System.out.print("Enter Number of Columns: ");
            int n = sc.nextInt();
            int[] key = new int[n];
            System.out.println("Enter Key:");
            for (int i = 0; i < n; i++) {
                key[i] = sc.nextInt();
            }
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            out.writeUTF(text);
            out.writeInt(n);
            for (int i = 0; i < n; i++) {
                out.writeInt(key[i]);
            }
            
            String encrypted = in.readUTF();
            System.out.println("Cipher Text: " + encrypted);
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
