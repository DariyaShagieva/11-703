package ru.itis;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Считать все значения из файла");
        GraphicPic newGP = new GraphicPic("Segments.txt");
        newGP.show();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Добавление нового отрезка с учетом того,");
        System.out.println("что он не существует");
        //т.к. потоки разные - serr выбрасывается раньше чем show
        //здесь чисто фактическая проверка, что не добавляет
        GraphicPic.Segment existed = new GraphicPic.Segment(6, 7, 9, -9);
        newGP.insert(existed);
        GraphicPic.Segment unExisted = new GraphicPic.Segment(1, -4, 5, 12);
        newGP.insert(unExisted);
        newGP.show();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Построение нового списка,");
        System.out.println("состоящего из отресков, наклоненных к оси абсцисс");
        System.out.println("под углами 30 и 45 грудусов");
        GraphicPic angleList = newGP.angleList();
        //не факт, что существует, я точки беру рандомом
        //может ничего не показать
        angleList.show();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Построение нового списка,");
        System.out.println("состоящего из отрезков, находящихся в интервале от a до b");
        GraphicPic lengthList = newGP.lengthList(4, 7);
        lengthList.show();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Сортировка");
        newGP.sortByMerge();
        newGP.show();
    }
}
