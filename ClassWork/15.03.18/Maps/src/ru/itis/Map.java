package ru.itis;

/**
 * 15.03.2018
 * Map
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface Map<K, V> {
    void put(K key, V value);
    V get(K key);
}
