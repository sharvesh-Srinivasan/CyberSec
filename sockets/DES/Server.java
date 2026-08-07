
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Server {

    public static String encrypt(String message, String key) throws Exception {
        if (key.length() != 8) {
            throw new Exception("DES Key must be exactly 8 bytes.");
        }
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedText, String key) throws Exception {
        if (key.length() != 8) {
            throw new Exception("DES Key must be exactly 8 bytes.");
        }
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = Base64.getDecoder().decode(encryptedText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5011)) {
            System.out.println("DES Server listening on port 5011...");
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected!");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String message = dis.readUTF();
            String key = dis.readUTF();
            
            try {
                String encrypted = encrypt(message, key);
                String decrypted = decrypt(encrypted, key);
                
                dos.writeUTF(encrypted);
                dos.writeUTF(decrypted);
            } catch (Exception e) {
                dos.writeUTF("Error: " + e.getMessage());
                dos.writeUTF("Error: " + e.getMessage());
            }

            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
