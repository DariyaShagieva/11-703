package ru.itis;

import javax.swing.text.Segment;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    GraphicPic graphic = new GraphicPic("Segments.txt");
//        graphic.show();
//        graphic.sort();
//        graphic.angleList().show();
        GraphicPic newGP = graphic.lengthList(2, 3);
        newGP.show();
//        graphic.show();
        int i = 0;
    }
}
