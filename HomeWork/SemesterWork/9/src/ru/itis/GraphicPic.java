package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class GraphicPic {



    public static class Segment {
        private int x1;
        private int x2;
        private int y1;
        private int y2;
        private double length;
        private Segment next;


        public Segment getNext() {
            return next;
        }

        public int getX1() {

            return x1;
        }

        public int getX2() {
            return x2;
        }

        public int getY1() {
            return y1;
        }

        public int getY2() {
            return y2;
        }

        public double getLength() {
            return length;
        }


        public Segment(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.length = lengthFounder();
        }

        @Override
        public String toString() {
            return "Координаты точки начала отрезка: (" + x1 + ", " + y1 + ") \n" + "Координаты точки конца отрезка: (" + x2 + ", " + y2 + ") \n";
        }

        private double lengthFounder() {
            int x = x2 - x1;
            int y = y2 - y1;
            return Math.sqrt(x * x + y * y);
        }

    }

    private Segment head;
    private Segment tail;
    private int count;


    public Segment getHead() {
        return head;
    }

    public Segment getTail() {
        return tail;
    }

    public int getCount() {
        return count;
    }

    //ПОСТРОЕНИЕ СПИСКА ПО МНОЖЕСТВУ ОТРЕЗКОВ, ЗАДАННЫХ В ТЕКСТОВОМ ФАЙЛЕ
    public GraphicPic(String fileName) {
        this.count = 0;
        try {
            Scanner file = new Scanner(new File(fileName));
            while (file.hasNext()) {
                String currentSegment = file.nextLine();
                int[] currentSegmentAsIntArr = stringToInt(currentSegment.split(" "));
                Segment segment = new Segment(currentSegmentAsIntArr[0], currentSegmentAsIntArr[1],
                        currentSegmentAsIntArr[2], currentSegmentAsIntArr[3]);
                insert(segment);
            }
        } catch (FileNotFoundException e) {
//      В случае catch буде просто создаваться пустой GraphicPic
        }

    }

    //ВЫВОД ВСЕХ ОТРЕЗКОВ И ИНФОРМАЦИИ О НИХ НА ЭКРАН
    public void show() {
        Segment current = head;
        while (current != null) {
            System.out.println(current.toString());
            current = current.next;
        }
    }

    //ВСТАВКА ОТКРЕЗКА В СПИСОК. ПРИ ВСТАВКЕ УЧЕСТЬ, СУЩЕСТВУЕТ ЛИ ПОДОБНЫЙ ЭЛЕМЕНТ В СПИСКЕ. ЕСЛИ ЕСТЬ - НЕ ДОБАВЛЯТЬ
    public void insert(Segment f) {
        if (head == null) {
            tail = f;
            head = f;
            return;
        }
        Segment current = head;
        while (current != null) {
            if (f.y1 == current.y1 && f.x2 == current.x2 && f.x1 == current.x1 && f.y2 == current.y2) {
                System.err.println("Бессмысленно - отрезок уже имеется");
                return;
            }
            current = current.next;
        }
        tail.next = f;
        tail = f;
        count++;
    }

    //ПОСТОРОИТЬ НОВЫЙ СПИСОК, СОСТОЯЩИЙ ИЗ ОТРЕЗКОВ, НАКЛОНЕННЫХ К ОСИ АБСЦИСС ПОД УГЛАМИ 30 И 45 ГРАДУСОВ
    public GraphicPic angleList() {
        GraphicPic angleList = new GraphicPic("filename.txt");
        Segment current = head;
        double cos30 = Math.sqrt(3) / 2;
        double cos45 = Math.sqrt(2) / 2;
        while (current != null) {
            int xLength = Math.abs(current.x1 - current.x2);
            double cos = xLength / current.length;
            if (cos == cos30 || cos == cos45) {
                Segment currentSegment = new Segment(current.x1, current.y1, current.x2, current.y2);
                angleList.insert(currentSegment);
            }
            current = current.next;
        }
        return angleList;
    }


    //ПОСТРОИТЬ НОВЫЙ СПИСОК ИЗ ОТРЕЗКОВ, ДЛИНА КОТОРЫХ НАХОДИТСЯ В ИНТЕРВАЛЕ [a, b]
    public GraphicPic lengthList(int a, int b) {
        Segment current = head;
        GraphicPic fromAToB = new GraphicPic("null");
        while (current != null) {
            if (current.length >= a && current.length <= b) {
                Segment added = new Segment(current.x1, current.y1, current.x2, current.y2);
                fromAToB.insert(added);
            }
            current = current.next;
        }
        return fromAToB;
    }

    //УПОРЯДОЧИТЬ СПИСОК ОТРЕЗКОВ ПО ВОЗРАСТАНИЮ ДЛИН
    public void sortByMerge() {
        GraphicPic[] pics = new GraphicPic[64];
        Segment current = head;
        pics[0] = new GraphicPic("null");
        Segment toAdd = new Segment(current.x1, current.y1, current.x2, current.y2);
        current = current.next;
        pics[0].insert(toAdd);
        pics[1] = new GraphicPic("null");
        toAdd = new Segment(current.x1, current.y1, current.x2, current.y2);
        current = current.next;
        pics[1].insert(toAdd);
        int el = 1;
        while (current != null) {
            if (el != 0) {
                if (pics[el].getCount() == pics[el - 1].getCount()) {
                    pics[el - 1] = merge(pics[el], pics[el - 1]);
                    pics[el] = null;
                    el--;
                } else {
                    el++;
                    pics[el] = new GraphicPic("null");
                    toAdd = new Segment(current.x1, current.y1, current.x2, current.y2);
                    current = current.next;
                    pics[el].insert(toAdd);
                }
            } else {
                el++;
                pics[el] = new GraphicPic("null");
                toAdd = new Segment(current.x1, current.y1, current.x2, current.y2);
                current = current.next;
                pics[el].insert(toAdd);
            }
        }

        for (int i = el; i > 0; i--) {
            pics[i - 1] = merge(pics[i], pics[i - 1]);
        }

        head = pics[0].head;
        tail = pics[0].tail;
    }

    //СЛИЯНИЕ
    private static GraphicPic merge(GraphicPic a, GraphicPic b) {
        if(a.head == null) return b;
        if(b.head == null) return a;

        GraphicPic merged = new GraphicPic("Null");

        Segment currentA = a.head;
        Segment currentB = b.head;
        Segment toAdd;

        while (currentA != null && currentB != null) {
            if (currentA.getLength() < currentB.getLength()) {
                toAdd = new Segment(currentA.getX1(), currentA.getY1(), currentA.getX2(), currentA.getY2());
                merged.insert(toAdd);
                currentA = currentA.next;
            } else {
                toAdd = new Segment(currentB.getX1(), currentB.getY1(), currentB.getX2(), currentB.getY2());
                merged.insert(toAdd);
                currentB = currentB.next;
            }
        }

        if (currentA != null) {
            while (currentA != null) {
                toAdd = new Segment(currentA.getX1(), currentA.getY1(), currentA.getX2(), currentA.getY2());
                merged.insert(toAdd);
                currentA = currentA.next;
            }
        } else {
            if (currentB != null) {
                while (currentB != null) {
                    toAdd = new Segment(currentB.getX1(), currentB.getY1(), currentB.getX2(), currentB.getY2());
                    merged.insert(toAdd);
                    currentB = currentB.next;
                }
            }
        }
        return merged;
    }

    private int[] stringToInt(String[] intAsString) {
        int[] ints = new int[intAsString.length];
        for (int counts = 0; counts < intAsString.length; counts++) {
            ints[counts] = Integer.parseInt(intAsString[counts]);
        }
        return ints;
    }
}
