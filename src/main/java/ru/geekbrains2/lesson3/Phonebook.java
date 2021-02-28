package ru.geekbrains2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Phonebook {
    private final HashMap<String, Set<String>> map = new HashMap<>();

    public Set<String> get(String key) {
        HashSet<String> set = (HashSet<String>) map.get(key);
        if (set == null) {
            set = new HashSet<>();
        }
        return set;
    }

    public Set<String> add(String key, String value) {
        Set<String> set = get(key);
        set.add(value);
        return map.put(key, set);
    }
}
