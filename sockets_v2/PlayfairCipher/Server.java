package sockets_v2.PlayfairCipher;

import java.io.*;
import java.net.*;

public class Server {

    static char[][] generateMatrix(String key) {
        char[][] matrix = new char[5][5];
        boolean[] alphabet = new boolean[26];
        key = key.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        int row = 0, col = 0;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!alphabet[ch - 'A']) {
                matrix[row][col] = ch;
                alphabet[ch - 'A'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue;
            if (!alphabet[ch - 'A']) {
                matrix[row][col] = ch;
                alphabet[ch - 'A'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
        return matrix;
    }

    static int[] findPosition(char ch, char[][] matrix) {
        if (ch == 'J') ch = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) return new int[]{i, j};
            }
        }
        return null;
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (i + 1 < text.length()) {
                if (text.charAt(i) == text.charAt(i + 1)) sb.append('X');
            }
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    public static String encrypt(String text, String key) {
        char[][] matrix = generateMatrix(key);
        text = prepareText(text);
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
            int[] p1 = findPosition(a, matrix);
            int[] p2 = findPosition(b, matrix);
            if (p1[0] == p2[0]) {
                cipher.append(matrix[p1[0]][(p1[1] + 1) % 5]);
                cipher.append(matrix[p2[0]][(p2[1] + 1) % 5]);
            } else if (p1[1] == p2[1]) {
                cipher.append(matrix[(p1[0] + 1) % 5][p1[1]]);
                cipher.append(matrix[(p2[0] + 1) % 5][p2[1]]);
            } else {
                cipher.append(matrix[p1[0]][p2[1]]);
                cipher.append(matrix[p2[0]][p1[1]]);
            }
        }
        return text + ";" + cipher.toString(); // Return both prepared text and cipher text separated by ;
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5009)) {
            System.out.println("PlayfairCipher Server listening on port 5009...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            String key = in.readUTF();
            String text = in.readUTF();
            
            String result = encrypt(text, key);
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
