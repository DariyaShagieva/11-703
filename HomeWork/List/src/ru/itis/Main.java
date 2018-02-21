package ru.itis;


import ru.itis.Elements.List;

public class Main {

    public static void main(String[] args) {
        List myList = new List();
        myList.addFront("2");
        myList.addFront('1');
        myList.addFront(4);
        myList.addBack("Два");
        myList.printMyList();
    }
}
