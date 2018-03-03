package ru.itis;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        RandomFileCreator creator = new RandomFileCreator(10, "Segments.txt");
//        creator.writeToFile();
        GraphicPic graphic1 = new GraphicPic("Segments.txt");
        GraphicPic graphic2 = new GraphicPic("Segments.txt");
        graphic1.sortAsMudak();
        graphic2.sortByMerge();
        graphic1.show();
        System.out.println();
        System.out.println();
        System.out.println();
        graphic2.show();
//        GraphicPic np = new GraphicPic("null");
        int c = 0;
//        graphic1.show();
//        graphic2.show();
//        GraphicPic newPic = GraphicPic.merge(graphic1, graphic2);
//        graphic1.show();
//        System.out.println();
//        System.out.println();
//        graphic2.show();
//        System.out.println();
//        System.out.println();
//        newPic.show();
//        graphic.angleList().show();
//        GraphicPic newGP = graphic.lengthList(2, 3);
//        newGP.show();
//        graphic.show();
    }
}
