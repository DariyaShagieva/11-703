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
    public void setUp() {
        test = new GraphicPic("Segments.txt");
    }

    @Test
    public void constructorTest() {
        GraphicPic.Segment current = test.getHead();
        Boolean equals = true;
        try {
            Scanner file = new Scanner(new File("Segments.txt"));
            while (current != null || file.hasNext()) {
                String[] currentLine = file.nextLine().split(" ");
                if (current.getX1() != Integer.parseInt(currentLine[0]) || current.getY1() != Integer.parseInt(currentLine[1]) ||
                        current.getX2() != Integer.parseInt(currentLine[2]) || current.getY2() != Integer.parseInt(currentLine[3])) {
                    equals = false;
                    assertTrue(equals);
                }
                current = current.getNext();
            }
        } catch (FileNotFoundException e) {
            equals = false;
        }
        assertTrue(equals);
    }

    @Test
    public void insertTest() {
        boolean equals = false;
        String[] toTestPrev = makeString();
        test.insert(new GraphicPic.Segment(Integer.parseInt(toTestPrev[0].split(" ")[0]),
                Integer.parseInt(toTestPrev[0].split(" ")[1]),
                Integer.parseInt(toTestPrev[0].split(" ")[2]),
                Integer.parseInt(toTestPrev[0].split(" ")[3])));
        String[] toTestAfter = makeString();
        if (toTestAfter.length != toTestPrev.length) {
            assertTrue(equals);
        }
        test.insert(new GraphicPic.Segment(9999, 9999, 9999, 9999));
        toTestAfter = makeString();
        if (toTestAfter.length != toTestPrev.length + 1) {
            assertTrue(equals);
        }
        if (toTestAfter[toTestAfter.length - 1].equals("9999 9999 9999 9999 0.0")) {
            equals = true;
            assertTrue(equals);
        } else {
            assertTrue(equals);
        }
    }

    @Test
    public void sortTest() {
        int before = makeString().length;
        test.sortByMerge();
        String[] sorted = makeString();
        if (before != sorted.length) {
            assertTrue(false);
        }
        for (int i = 0; i < before - 1; i++) {
            if (Double.parseDouble(sorted[i].split(" ")[4]) >
                    Double.parseDouble(sorted[i + 1].split(" ")[4])) {
                assertTrue(false);
            }
        }
        assertTrue(true);
    }

    @Test
    public void lengthListTest() {
        String[] before = makeString();
        test = test.lengthList(3, 7);
        String[] lengthListString = makeString();
        String[] founded = new String[before.length];
        int count = 0;

        for (int i = 0; i < before.length; i++) {
            double n = Double.parseDouble(before[i].split(" ")[4]);
            if (n >= 3 && n <= 7) {
                founded[count] = before[i];
                count++;
            }
        }
        String[] shortFounded = new String[count];
        for (int c = 0; c < count; c++) {//с++....
            shortFounded[c] = founded[c];
        }
        for (int c = 0; c < count; c++) {
            if (!shortFounded[c].equals(lengthListString[c])) assertTrue(false);
        }
        assertTrue(true);
    }

    private String[] makeString() {
        GraphicPic.Segment current = test.getHead();
        String[] asString = new String[test.getCount() + 1];
        int num = 0;
        while (current != null) {
            asString[num] = current.getX1() + " " + current.getY1() + " " + current.getX2()
                    + " " + current.getY2() + " " + current.getLength();
            current = current.getNext();
            num++;
        }
        return asString;
    }
}
