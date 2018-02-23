package ru.itis;

public class Main {

    public static void main(String[] args) {
	    LinkedList list = new LinkedList();
	    list.add(20);
	    list.add("Ильяс");
	    list.add("Влад 195");
	    list.add("Андрей");
        list.addToBegin("Nita");
        list.add(45);
        list.add(45);
        list.add("Армейка");
        list.remove("Андрей");
        list.add("Bayrasheva");
        list.add("I wanna go home");
        list.remove("Nita");
        list.remove("Армейка");
        list.add("Андрей");
        list.addToBegin("Marsel");
        list.remove("I wanna go home");
        list.add("Nita");
        list.remove("Bayrasheva");
//        System.out.println(list.contains("I wanna home"));
        list.show();
        list.reverse();
        list.show();
        LinkedList <Integer> list1 = new LinkedList<>();
        LinkedList <Integer> list2 = new LinkedList<>();
        list2.add(2);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list2.add(8);
        list2.add(9);
        list2.add(11);
        list2.add(12);
        list2.add(16);
        list2.add(17);
        list2.add(19);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        list1.add(7);
        list1.add(8);
        list1.add(9);
        list1.add(10);
        list1.add(11);
        list1.add(12);
        list1.add(13);
        LinkedList merged = LinkedList.merge(list1, list2);
        merged.show();
        int i = 0;
    }
}
