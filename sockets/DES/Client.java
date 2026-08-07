import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5011)) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Message : ");
            String message = sc.nextLine();
            
            System.out.print("Enter 8-byte DES Key (e.g. 12345678): ");
            String key = sc.nextLine();
            
            while (key.length() != 8) {
                System.out.print("Invalid key length! Please enter exactly 8 characters: ");
                key = sc.nextLine();
            }

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            dos.writeUTF(message);
            dos.writeUTF(key);

            String encrypted = dis.readUTF();
            String decrypted = dis.readUTF();

            System.out.println("\nEncrypted Message received from server:");
            System.out.println(encrypted);
            
            System.out.println("\nDecrypted Message received from server:");
            System.out.println(decrypted);

            dos.flush();
            dos.close();
            dis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}