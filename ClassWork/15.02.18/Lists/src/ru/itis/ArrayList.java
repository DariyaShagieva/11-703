package ru.itis;

/**
 * 15.02.2018
 * ArrayList
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class ArrayList implements List {

    private static final int DEFAULT_SIZE = 10;

    private Object elements[];

    private int count;

    public ArrayList() {
        this.elements = new Object[DEFAULT_SIZE];
        this.count = 0;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < count; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object get(int index) {
        if (index >= 0 && index < count) {
            return elements[index];
        } else throw new IllegalArgumentException();
    }

    @Override
    public void addToBegin(Object element) {

    }

    @Override
    public void add(Object element) {
        if (count < elements.length) {
            this.elements[count++] = element;
        } else throw new IllegalArgumentException();
    }

    @Override
    public void remove(Object element) {

    }

    @Override
    public boolean contains(Object element) {
        return false;
    }

    @Override
    public int size() {
        return count;
    }
}
