package ru.itis;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static junit.framework.TestCase.assertTrue;

public class GraphicPicTest {

    GraphicPic test;

    @Before
    public void setUp (){
        test = new GraphicPic("Segments.txt");
    }

    @Test
    public void constructorTest (){
        GraphicPic.Segment current = test.getHead();
        Boolean equals = true;
        try {
            Scanner file = new Scanner(new File("Segments.txt"));
            while (current != null || file.hasNext()){
                String [] currentLine = file.nextLine().split(" ");
                if (current.getX1() != Integer.parseInt(currentLine[0]) || current.getY1() != Integer.parseInt(currentLine[1]) ||
                        current.getX2() != Integer.parseInt(currentLine[2]) || current.getY2() != Integer.parseInt(currentLine[3])) equals = false;
                current = current.getNext();
            }
        } catch (FileNotFoundException e) {
            equals = false;
        }
        assertTrue(equals);
    }

    @Test
    public void insertTest (){
        GraphicPic.Segment newSegment = new GraphicPic.Segment(2, 4, 6, 8);
        test.insert(newSegment);
        GraphicPic.Segment current = test.getHead();
        while (current.getNext() != null){
            current = current.getNext();
        }
        boolean equals;
        if (current.getY1() == newSegment.getY1() && current.getY2() == newSegment.getY2() && current.getX2()
                == newSegment.getX2() && current.getX1() == newSegment.getX1()){
            equals = true;
        } else equals = false;
       assertTrue(equals);
    }

    @Test
    public void sortTest (){
        boolean sorted = true;
        test.sortAsMudak();
        GraphicPic.Segment current = test.getHead();
        double prev = current.getLength();
        current = current.getNext();
        while (current != null){
            if (prev > current.getLength()) sorted = false;
            current = current.getNext();
        }
        assertTrue(sorted);
    }

    @Test
    public void lengthListTest (){
        boolean equals = true;
        boolean hasThis = false;
        GraphicPic.Segment current = test.getHead();
        while (current != null && !hasThis){
            if (current.getLength() >= 7 && current.getLength() <= 10){
                hasThis = true;
            }
            current = current.getNext();
        }
        GraphicPic from7to10 = test.lengthList(7, 10);
        if (!hasThis && from7to10.getCount() == 0){
            assertTrue(equals);
        }
        current = from7to10.getHead();
        while (current != null && equals){
            if (current.getLength() < 7 || current.getLength() > 10) equals = false;
            current = current.getNext();
        }
        assertTrue(equals);
    }

//    @Test
//    public
}
