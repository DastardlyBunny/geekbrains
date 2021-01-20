package ru.geekbrains.lesson3;

import java.util.Random;
import java.util.Scanner;

public class MainApp {

    private final static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    private final static StringBuilder hashtagString = new StringBuilder("###############");

    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(words.length);

        playGame(words[randomNumber]);
    }

    private static void playGame(String randomWord) {
        Scanner scanner = new Scanner(System.in);
        String userWord;
        do {
            System.out.println(hashtagString);
            System.out.println("Введите загаданное слово:");

            userWord = scanner.nextLine().toLowerCase();

            for (int i = 0; i < hashtagString.length(); i++) {
                if (i < randomWord.length() && i < userWord.length()) {
                    if (userWord.charAt(i) == randomWord.charAt(i)) {
                        hashtagString.setCharAt(i, userWord.charAt(i));
                        continue;
                    }
                }
                hashtagString.setCharAt(i, '#');
            }
        } while (!userWord.equals(randomWord));

        System.out.println("Вы угадали!");
    }
}
