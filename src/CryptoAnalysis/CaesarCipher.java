package CryptoAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaesarCipher {

    private static final String stringAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,!][;:";

    private static final char[] charAlphabet = stringAlphabet.toCharArray();


    public void encrypt(String inputFile, String outputFile, int key) {
        String fileForEncrypt = readFile(inputFile);

        char[] charsForEncrypt = fileForEncrypt.toCharArray();

        for (int i = 0; i < charsForEncrypt.length; i++) {
            int index = stringAlphabet.indexOf(charsForEncrypt[i]);

            if (index != -1) {

                int encryptedIndex = (index + key) % stringAlphabet.length();

                if (encryptedIndex < 0) {
                    encryptedIndex = stringAlphabet.length() + encryptedIndex;
                }

                charsForEncrypt[i] = stringAlphabet.charAt(encryptedIndex);
            }
        }
        writeFile(outputFile, new String(charsForEncrypt));
    }

    public void decrypt(String inputFile, String outputFile, int key) {
        encrypt(inputFile, outputFile, -key);
    }

    public void bruteForce(String inputFile) {
        String fileForBrute = readFile(inputFile);

        char[] bruteChars = fileForBrute.toCharArray();

        for (int key = 1; key < stringAlphabet.length(); key++) {

            System.out.println("Попытка взлома с ключом " + key + ": " + "\n");

            for (int i = 0; i < bruteChars.length; i++) {
                int index = stringAlphabet.indexOf(bruteChars[i]);

                if (index != -1) {
                    int indexBrute = (index - key) % stringAlphabet.length();

                    if (indexBrute < 0) {
                        indexBrute = stringAlphabet.length() + indexBrute;
                    }
                    bruteChars[i] = stringAlphabet.charAt(indexBrute);
                }
            }
            System.out.println(new String(bruteChars) + "\n");
        }
    }

    private String readFile(String filePath) {
        String result = "";
        Path path = Path.of(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void writeFile(String filePathToWrite, String objectToWrite) {
        Path path = Path.of(filePathToWrite);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(objectToWrite, 0, objectToWrite.length());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
