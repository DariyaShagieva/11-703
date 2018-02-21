package ru.itis;

/**
 * 15.02.2018
 * LinkedList
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class LinkedList implements List {

    private static class Node {
        Object value;
        Node next;

        Node(Object value) {
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
    public int indexOf(Object element) {
        Node checking = head;
        for (int current = 0; current < count; current++) {
            if (head.value.equals(element)) return current;
            checking = checking.next;
        }
        return -1;
    }

    @Override
    public Object get(int index) {
        Node currentElement = head;
        for (int current = 0; current < index; current++) {
            currentElement = currentElement.next;
        }
        return currentElement.value;
    }

    @Override
    public void addToBegin(Object element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        count++;
    }

    @Override
    public void add(Object element) {
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
    public void remove(Object element) {
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
    public boolean contains(Object element) {
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
        while (current.next != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.print(current.value);
        System.out.println();
    }
}
