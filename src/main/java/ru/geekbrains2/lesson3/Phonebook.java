package ru.geekbrains2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Phonebook {
    private final HashMap<String, Set<String>> map = new HashMap<>();

    public Set<String> get(String key) {
        HashSet<String> set = (HashSet<String>) map.get(key);
        if (map.get(key) == null) {
            set = new HashSet<>();
        }
        return set;
    }

    public Set<String> add(String key, Set<String> value) {
        return map.put(key, value);
    }
}
