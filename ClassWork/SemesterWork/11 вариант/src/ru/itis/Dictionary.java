package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {

    private static class Translations {
        Word value;
        Translations next;

        Translations(Word value) {
            this.value = value;
        }
    }

    private static class Word{
        String word;
        String translation;

        public Word(String word, String translation) {
            this.word = word;
            this.translation = translation;
        }

        public String getWord() {
            return word;
        }

        public String getTranslation() {
            return translation;
        }

        @Override
        public String toString() {
            return getWord() + " " + getTranslation();
        }
    }


    private Translations head;
    private Translations tail;
    int count;

    public Dictionary(String fileName) {
        try {
            Scanner dictionary = new Scanner(new File(fileName));
            String currentString = dictionary.nextLine();
            String[] currentTranslation = currentString.split(" ");
            Translations newTranslation = new Translations(new Word(currentTranslation[0], currentTranslation[1]));
            head = newTranslation;
            tail = newTranslation;
            while ((currentString = dictionary.nextLine()) != null) {
                currentTranslation = currentString.split(" ");
                insert(currentTranslation[0], currentTranslation[1]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Не прочитан файл");
            throw new IllegalArgumentException(e);
        }
    }


    public void show() {
        Translations current = head;
        while (current.next != null) {
            System.out.println(current.value.toString());
            current = current.next;
        }
        System.out.print(current.value.toString());
    }

    public void unique() {

        while (true){

        }
    }

    public int numLen1() {
        int count = 0;
        Translations all = head;
        while (all.next != null) {
            int value1 = all.value.getTranslation().length();
            int value2 = all.value.getWord().length();
            if (value1 == value2 || value1 + 1 == value2 || value1 == value2 + 1) {
                count++;
            }
        }
        return count;
    }

    public String translate(String text) {
        String[] textToArr = text.split(" ");
        String translatedText = "";
        Translations all = head;
        for (int count = 0; count < textToArr.length; count++) {
            while (!all.value.getWord().equals(textToArr[count])) {
                all = all.next;
            }
            translatedText += all.value.getTranslation();
            all = head;
        }
        return translatedText;
    }

    public void insert(String k, String v) {
        Translations newTranslation = new Translations(new Word(k, v));
        tail.next = newTranslation;
        tail = newTranslation;
        count++;
    }
}