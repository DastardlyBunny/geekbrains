package ru.geekbrains2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class MainApp {
    private static final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "apple", "lemon", "cherry", "apple"};

    public static void main(String[] args) {
        System.out.println(getIdenticalWordCount());

        Set<String> hashSet = new HashSet<>();
        hashSet.add("89020000001");
        hashSet.add("89020000002");

        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", hashSet);
        System.out.println(phonebook.get("Иванов"));
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
