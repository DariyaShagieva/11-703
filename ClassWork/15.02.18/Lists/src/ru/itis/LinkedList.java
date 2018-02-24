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

    public  <E extends Comparable<E>> void sort(){
        Node outWay = head;
        Node inWay;
        while (outWay.next != null){
            inWay = outWay.next;
            while (inWay.next != null){
                if (outWay.value.compareTo(inWay.value) < 0){
                    E toCorrect = inWay.value;
                    inWay.value = outWay.value;
                    outWay.value = toCorrect;
                }
                inWay = inWay.next;
            }
            outWay = outWay.next;
        }
    }

    public static <E extends Comparable <E>> LinkedList merge(LinkedList<E> a, LinkedList<E> b) {
        if (a.head == null) return b;
        if (b.head == null) return a;
        Iterator<E> iteratorA = a.iterator();
        Iterator<E> iteratorB = b.iterator();
        E toMerge1;
        E toMerge2;
        LinkedList <E> merged = new LinkedList<>();
        toMerge1 = iteratorA.next();
        toMerge2 = iteratorB.next();
        do {
            if (toMerge1.compareTo(toMerge2) < 0) {
                merged.add(toMerge1);
                toMerge1 = iteratorA.next();
            } else {
                merged.add(toMerge2);
                toMerge2 = iteratorB.next();
            }
        } while (iteratorA.hasNext() && iteratorB.hasNext());
        if (iteratorA.hasNext()) {
            merged.add(toMerge1);
            while (iteratorA.hasNext()) {
                merged.add(iteratorA.next());
            }
        } else {
            merged.add(toMerge2);
            while (iteratorB.hasNext()) {
                merged.add(iteratorB.next());
            }
        }
        return merged;
    }

}
