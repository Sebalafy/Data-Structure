package com.company;

public interface Map<K, V> {
    void add(K key, V value);
    V get(K key);
    void set(K key, V value);
    V remove(K key);
    int getSize();
    boolean isEmpty();
    boolean contains(K key);
}

