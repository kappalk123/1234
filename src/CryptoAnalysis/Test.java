package CryptoAnalysis;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String stringAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,!][;:";

        StringBuilder sb = new StringBuilder();

        String test = "ПрИвЕт, МиР!:";

        char[] chars = test.toCharArray();

        int[] indexChars = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {

            int indexFoEncrypt = stringAlphabet.indexOf(chars[i]);

            if (indexFoEncrypt != -1) {
                int newIndex = (indexFoEncrypt + 3) % stringAlphabet.length();
                chars[i] = stringAlphabet.charAt(newIndex);
            }
        }
        System.out.println(Arrays.toString(chars));

        char[] bruteChars = Arrays.copyOf(chars, chars.length);

        for (int key = 1; key < stringAlphabet.length(); key++) {

            System.out.println("Попытка взлома с ключом " + key + ": ");
            System.out.println();

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
}
