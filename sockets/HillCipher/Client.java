package sockets.HillCipher;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5008)) {
            Scanner sc = new Scanner(System.in);
            
            int[][] keyMatrix = new int[2][2];
            System.out.println("Enter 2x2 Key Matrix:");
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    keyMatrix[i][j] = sc.nextInt();
                }
            }
            sc.nextLine(); // consume newline
            
            System.out.print("Enter Plain Text (Even Length): ");
            String text = sc.nextLine();
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    out.writeInt(keyMatrix[i][j]);
                }
            }
            out.writeUTF(text);
            
            String encrypted = in.readUTF();
            System.out.println("Cipher Text: " + encrypted);
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
