package ru.itis;

import java.util.Iterator;

/**
 * 15.02.2018
 * LinkedList
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class LinkedList<E> implements List<E> {

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class Node {

        E value;
        Node next;

        Node(E value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    private int count;

    public LinkedList() {
        this.count = 0;
    }

    @Override
    public int indexOf(E element) {
        Node checking = head;
        for (int current = 0; current < count; current++) {
            if (head.value.equals(element)) return current;
            checking = checking.next;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        Node currentElement = head;
        for (int current = 0; current < index; current++) {
            currentElement = currentElement.next;
        }
        return currentElement.value;
    }

    @Override
    public void addToBegin(E element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        count++;
    }

    @Override
    public void add(E element) {
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }

    @Override
    public void remove(E element) {
        //если изначально пустой - проверка на идиотов
        if (head == null) return;
        //если один элемент - просто все обнуляем
        count--;
        if (head == tail) {
            head = null;
            tail = null;
            count = 0;
            return;
        }
        //если первый эл - искомый
        if (head.value.equals(element)) {
            head = head.next;
            return;
        }

        Node newNode = head;

        while (newNode != null) {
            if (newNode.next.value == element) {
                if (tail == newNode.next) {
                    tail = newNode;
                }
                newNode.next = newNode.next.next;
                return;
            }
            newNode = newNode.next;
        }
    }

    public void reverse() {
        LinkedList list = new LinkedList();
        Node current = head;
        while (current.next != null) {
            list.addToBegin(current.value);
            current = current.next;
        }
        list.addToBegin(current.value);
        head = list.head;
    }


    @Override
    public boolean contains(E element) {
        Node searcher = head;
        while (searcher.next != null) {
            if (searcher.value.equals(element)) return true;
            searcher = searcher.next;
        }
        if (searcher.value.equals(element)) return true;
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void show() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node current;

        public LinkedListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            return current != null;
        }


        @Override
        public E next() {
            Node cross = current;
            current = current.next;
            return cross.value;
        }
    }

    public void set (int index, E object){
        if (index > count) return;
        Node a = head;
        Node newNodeToIndex = new Node(object);
        for (int i = 0; i < index - 1; i++){
            a = a.next;
        }
        newNodeToIndex.next = a.next;
        a.next = newNodeToIndex;
    }


    public static <T extends Comparable<T>> LinkedList<T> merge(LinkedList<T> sorted1,
                                                                LinkedList<T> sorted2) {
        LinkedList<T> merged = new LinkedList<>();

        while (sorted1.head != null && sorted2.head != null) {
            if (sorted1.head.value.compareTo(sorted2.head.value) < 0) {
                merged.add(sorted1.head.value);
                sorted1.remove(sorted1.head.value);
            } else {
                merged.add(sorted2.head.value);
                sorted2.remove(sorted2.head.value);
            }
        }

        if (sorted1.head != null) {
            while (sorted2.head != null) {
                merged.add(sorted2.head.value);
                sorted2.remove(sorted2.head.value);
            }
        } else if (sorted2.head != null) {
            while (sorted1.head != null) {
                merged.add(sorted1.head.value);
                sorted1.remove(sorted1.head.value);
            }
        }

        return merged;
    }

}
