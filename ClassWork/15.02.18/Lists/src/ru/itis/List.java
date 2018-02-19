package ru.itis;

/**
 * 15.02.2018
 * List
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface List extends Collection {
    int indexOf(Object element);
    Object get(int index);
}
