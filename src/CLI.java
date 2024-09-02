import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private static String fileContent;
    public static int shift = 0;
    public static String suffixEncrypted = "[ENCRYPTED]";
    public static String suffixDecrypted = "[DECRYPTED]";
    public static String encryptChose = "encrypt";
    public static String decryptChose = "decrypt";
    public static String chose;
    public static String encrypted;
    public static String decrypted;

    //взаємодії з користувачем
    public static void Run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть шлях до файлу: ");
        String filePath = scanner.nextLine();
        FileManager fileManager = new FileManager();
        System.out.println("Введіть ключ(ціле число): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Введіть ціле число: ");
            scanner.next();
        }
        shift = scanner.nextInt();
        scanner.nextLine();

        do {
            System.out.println("Виберіть дію - ENCRYPT/DECRYPT: ");
            chose = scanner.nextLine();
        }
        while (!chose.equalsIgnoreCase(encryptChose) && !chose.equalsIgnoreCase(decryptChose));
        System.out.println(chose);
        scanner.close();

        try {
            fileContent = fileManager.readFile(filePath);
        } catch (
                IOException e) {
            System.out.println("IOException " + e);
        }

        if (chose.equalsIgnoreCase(encryptChose)) {
            encrypted = CaesarCipher.encrypt(fileContent, shift);
            System.out.println("Файл зашифровано - " + filePath);
            String newFilePath = String.valueOf(fileManager.createPathWithSuffix(filePath, suffixEncrypted));
            try {
                fileManager.writeFile(newFilePath, encrypted);
            } catch (IOException e) {
                System.out.println("IOException " + e);
            }
        }

        if (chose.equalsIgnoreCase(decryptChose)){
            decrypted = CaesarCipher.decrypt(fileContent, shift);
            System.out.println("Файл дешифровано - " + filePath);
            String newFilePath = String.valueOf(fileManager.createPathWithSuffix(filePath, suffixDecrypted));
            try {
                fileManager.writeFile(newFilePath, decrypted);
            } catch (IOException e) {
                System.out.println("IOException " + e);
            }
        }
    }

    public static void RunArgumentMode(String[] args){
        FileManager fileManager = new FileManager();
        try {
            fileContent = fileManager.readFile(args[1]);
        } catch (
                IOException e) {
            System.out.println("IOException " + e);
        }

        if (args[0].equalsIgnoreCase(encryptChose)) {
            encrypted = CaesarCipher.encrypt(fileContent, Integer.parseInt(args[2]));
            System.out.println("Файл зашифровано - " + args[1]);
            String newFilePath = String.valueOf(fileManager.createPathWithSuffix(args[1], suffixEncrypted));
            try {
                fileManager.writeFile(newFilePath, encrypted);
            } catch (IOException e) {
                System.out.println("IOException " + e);
            }
        }

        if (args[0].equalsIgnoreCase(decryptChose)){
            decrypted = CaesarCipher.decrypt(fileContent, Integer.parseInt(args[2]));
            System.out.println("Файл дешифровано - " + args[1]);
            String newFilePath = String.valueOf(fileManager.createPathWithSuffix(args[1], suffixDecrypted));
            try {
                fileManager.writeFile(newFilePath, decrypted);
            } catch (IOException e) {
                System.out.println("IOException " + e);
            }
        }
    }
}
