package CryptoAnalysis;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        CaesarCipher cipher = new CaesarCipher();

        Boolean isRunning = true;

        System.out.println("Вы запустили программу для работы с шифром Цезаря! Выберите опцию:");

        while (isRunning) {
            System.out.println("1) Зашифровать текст.");
            System.out.println("2) Расшифровать текст.");
            System.out.println("3) Наглый взлом текста.");
            System.out.println("4) Выход.");

            int userChoice = sc.nextInt();

            if (userChoice < 1 || userChoice > 4) {
                System.out.println("Выбрана недопустимая опция!");
            }


            if (userChoice == 1) {
                System.out.println("Введите путь к файлу, который хотите зашифровать:");
                String inputFile = sc.nextLine();
                inputFile = sc.nextLine();

                System.out.println("Введите путь к файлу, который сохранит шифр: ");
                String outputFile = sc.nextLine();

                System.out.println("Введите ключ шифра: ");
                int key = sc.nextInt();

                cipher.encrypt(inputFile, outputFile, key);
            }

            if (userChoice == 2) {
                System.out.println("Введите путь к файлу, который хотите расшифровать:");
                String inputFile = sc.nextLine();
                inputFile = sc.nextLine();

                System.out.println("Введите путь к файлу, который сохранит расшифровку: ");
                String outputFile = sc.nextLine();

                System.out.println("Введите ключ шифра: ");
                int key = sc.nextInt();

                cipher.decrypt(inputFile, outputFile, key);
            }

            if (userChoice == 3) {
                System.out.println("Введите путь к файлу, который хотите взломать:");
                String inputFile = sc.nextLine();
                inputFile = sc.nextLine();

                cipher.bruteForce(inputFile);
            }

            if (userChoice == 4) {
                isRunning = false;
            }
        }
    }
}
