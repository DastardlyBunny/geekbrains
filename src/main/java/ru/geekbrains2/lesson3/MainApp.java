package ru.geekbrains2.lesson3;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "apple", "lemon", "cherry", "apple"};

        MainApp app = new MainApp();
        System.out.println(app.getIdenticalWordCount(words));

        Phonebook phonebook = new Phonebook();

        Set<String> hashSet = new HashSet<>(Arrays.asList("89020000001", "89020000002"));
        phonebook.add("Иванов", hashSet);
        phonebook.add("Петров", "89020000003");

        System.out.println(phonebook.get("Иванов"));
        System.out.println(phonebook.get("Петров"));
    }

    private Map<String, Integer> getIdenticalWordCount(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 0);
            }
            map.put(word, map.get(word) + 1);
        }
        return map;
    }
}
