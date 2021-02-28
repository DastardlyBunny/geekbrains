package ru.geekbrains2.lesson3;

import java.util.HashMap;
import java.util.Map;

public class MainApp {
    private static final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "apple", "lemon", "cherry", "apple"};

    public static void main(String[] args) {
        System.out.println(getIdenticalWordCount());

        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", "89020000001");
        phonebook.add("Иванов", "89020000002");
        phonebook.add("Петров", "89020000003");
        System.out.println(phonebook.get("Иванов"));
        System.out.println(phonebook.get("Петров"));
    }

    private static Map<String, Integer> getIdenticalWordCount() {
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
