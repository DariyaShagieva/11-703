package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphicPic {

    private Segment head;
    private Segment tail;
    private int count;

    private class Segment {

        private int x1;
        private int x2;
        private int y1;
        private int y2;
        private double length;
        private Segment next;

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
            double founded = Math.sqrt(x * x + y * y);
            return founded;
        }
    }

    //
    private GraphicPic() {
        this.count = 0;
    }

    //ПОСТРОЕНИЕ СПИСКА ПО МНОЖЕСТВУ ОТРЕЗКОВ, ЗАДАННЫХ В ТЕКСТОВОМ ФАЙЛЕ
    public GraphicPic(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        String currentSegment = file.nextLine();
        this.count = 0;
        int[] currentSegmentAsIntArr = stringToInt(currentSegment.split(" "));
        Segment segment = new Segment(currentSegmentAsIntArr[0], currentSegmentAsIntArr[1],
                currentSegmentAsIntArr[2], currentSegmentAsIntArr[3]);
        this.count++;
        head = segment;
        tail = segment;
        while (file.hasNext()) {
            currentSegment = file.nextLine();
            currentSegmentAsIntArr = stringToInt(currentSegment.split(" "));
            segment = new Segment(currentSegmentAsIntArr[0], currentSegmentAsIntArr[1],
                    currentSegmentAsIntArr[2], currentSegmentAsIntArr[3]);
            insert(segment);
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
        GraphicPic angleList = new GraphicPic();
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
        GraphicPic fromAToB = new GraphicPic();
        boolean firstElementNotFounded = true;

        while (current != null && firstElementNotFounded) {
            if (current.length >= a && current.length <= b) {
                Segment added = new Segment(current.x1, current.y1, current.x2, current.y2);
                fromAToB.head = added;
                fromAToB.tail = added;
                firstElementNotFounded = false;
            }
            current = current.next;
        }

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
    public void sort() {
        Segment inWay = head.next;
        Segment outWay = head;
        Segment cross = head;
        Segment cross2 = null;
        while(outWay != null) {
            while(inWay.next != null) {
                if(outWay.length > inWay.length) {
                    Segment obj = outWay.next;
                    outWay.next = inWay.next;
                    inWay.next = obj;
                    cross.next = outWay;
                    if (cross == null){

                    }
                    if (cross2 != null){
                        cross2.next = inWay;
                    }
                }
                inWay = inWay.next;
                cross = cross.next;
            }
            cross2 = outWay;
            outWay = outWay.next;
            cross = outWay;
        }
    }

    private int[] stringToInt(String[] intAsString) {
        int[] ints = new int[intAsString.length];
        for (int count = 0; count < intAsString.length; count++) {
            ints[count] = Integer.parseInt(intAsString[count]);
        }
        return ints;
    }
}
