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
        int i = 0;
    }
}
