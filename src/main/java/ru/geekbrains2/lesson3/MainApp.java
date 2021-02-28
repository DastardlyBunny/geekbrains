package ru.geekbrains2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainApp {
    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "apple", "lemon", "cherry", "apple"};

        MainApp app = new MainApp();
        System.out.println(app.getIdenticalWordCount(words));

        Set<String> hashSet = new HashSet<>();
        hashSet.add("89020000001");
        hashSet.add("89020000002");

        Phonebook phonebook = new Phonebook();
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
