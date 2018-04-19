package ru.itis;

/**
 * 04.04.2018
 * BinarySearchTree
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface BinarySearchTree<T extends Comparable> {
    void insert(T element);
    boolean remove(T element);
    boolean contains(T element);
    void print();
    void printByLevels();
}
