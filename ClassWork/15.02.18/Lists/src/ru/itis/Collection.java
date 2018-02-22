package ru.itis;

/**
 * 15.02.2018
 * Collection
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface Collection <T> {
    void addToBegin(T element);
    void add(T element);
    void remove(T element);
    boolean contains(T element);
    int size();
    void show();
}
