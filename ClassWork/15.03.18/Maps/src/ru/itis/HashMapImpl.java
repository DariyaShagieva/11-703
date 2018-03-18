package ru.itis;


/**
 * 15.03.2018
 * HashMapImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class HashMapImpl<K, V> implements Map<K, V> {

    private KeyValue[] map;

    private class KeyValue<K, V> {
        private Node head;
        private Node tail;

        private void add(K key, V value) {
            Node newNode = new Node(key, value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        private int contains(K key) {
            Node current = head;
            int count = 0;
            while (current != null) {
                if (current.getKey().equals(key)) {
                    return count;
                }
                current = current.getNext();
            }
            return -1;
        }

        private void set(int count, V value) {
            Node current = head;
            for (int c = 0; c < count; c++) {
                current = current.next;
            }
            current.value = value;
        }
    }

    //NODE на 3 переменные: next, value, key
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }


    HashMapImpl() {
        map = new KeyValue[10];
    }

    @Override
    public void put(K key, V value) {
        int position = Math.abs(key.hashCode() % 10);
        if (map[position] == null) {
            map[position] = new KeyValue();
            map[position].add(key, value);
            return;
        }
        int count;
        if ((count = map[position].contains(key)) >= 0) {
            map[position].set(count, value);
        } else {
            map[position].add(key, value);
        }
    }


    @Override
    public V get(K key) {
        int position = Math.abs(key.hashCode() % 10);
        boolean unFounded = true;
        if (map[position] == null) return null;
        Node current = map[position].head;
        while (unFounded && current != null) {
            if (current.getKey().equals(key)) {
                return (V) current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }
}
