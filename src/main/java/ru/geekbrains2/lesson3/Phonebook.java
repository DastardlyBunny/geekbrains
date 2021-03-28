package ru.geekbrains2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Phonebook {
    private final HashMap<String, Set<String>> book;

    public Phonebook() {
        this.book = new HashMap<>();
    }

    public Set<String> get(String key) {
        HashSet<String> set = (HashSet<String>) book.get(key);
        if (set == null) {
            set = new HashSet<>();
        }
        return set;
    }

    public Set<String> add(String key, String value) {
        Set<String> set = get(key);
        set.add(value);
        return book.put(key, set);
    }

    public Set<String> add(String key, Set<String> value) {
        return book.put(key, value);
    }
}
