import java.util.Scanner;

public class HillCipher {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] key = new int[2][2];

        System.out.println("Enter 2x2 Key Matrix:");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key[i][j] = sc.nextInt();
            }
        }

        sc.nextLine();

        System.out.print("Enter Plain Text (Even Length): ");
        String text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        if (text.length() % 2 != 0)
            text += "X";

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {

            int p1 = text.charAt(i) - 'A';
            int p2 = text.charAt(i + 1) - 'A';

            int c1 = (key[0][0] * p1 + key[0][1] * p2) % 26;
            int c2 = (key[1][0] * p1 + key[1][1] * p2) % 26;

            cipher.append((char) (c1 + 'A'));
            cipher.append((char) (c2 + 'A'));
        }

        System.out.println("Cipher Text: " + cipher);

        sc.close();
    }
}