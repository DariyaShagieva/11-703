package ru.itis;

/**
 * 15.02.2018
 * List
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface List <T> extends Collection <T> {
    int indexOf(<T> element);
    T get(int index);
}
