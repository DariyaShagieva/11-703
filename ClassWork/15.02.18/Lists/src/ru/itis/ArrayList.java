package ru.itis;

/**
 * 15.02.2018
 * ArrayList
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class ArrayList <T> implements List <T> {

    private static final int DEFAULT_SIZE = 10;

    private T elements[];

    private int count;

    public ArrayList() {
        this.elements = (T[])(new Object[DEFAULT_SIZE]);
        this.count = 0;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < count; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < count) {
            return elements[index];
        } else throw new IllegalArgumentException();
    }

    @Override
    public void addToBegin(Object element) {
        if (count == DEFAULT_SIZE) {
            System.err.println("Массив заполнен");
            return;
        }

        count++;
        if (elements[0] == null) {
            elements[0] = element;
            return;
        }

        T cross = elements[0];

        int i = 1;
        while (i < count + 1) {
            elements[0] = elements[count];
            elements[i] = cross;
            cross = elements[0];
            i++;
        }

        elements[0] = element;
    }

    @Override
    public void add(T element) {
        if (count < elements.length) {
            this.elements[count++] = element;
        } else throw new IllegalArgumentException();
    }

    @Override
    public void remove(T element) {
        int i = 0;
        if (elements[count - 1] == element) {
            elements[count - 1] = null;
            count--;
            return;
        }

        while (elements[i] != element) {
            i++;
        }
        for (int j = i; j < count - 1; j++) {
            elements[j] = elements[j + 1];
        }
        count--;
    }

    @Override
    public boolean contains(T element) {
        for (T obj : elements) {
            if (obj == element) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void show() {
        for (T obj : elements) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
}
