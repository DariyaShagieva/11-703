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

    private static class Word {
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

    public Dictionary(String fileName) {
        try {
            //СИТЫВАЕМ ФАЙЛ С ПЕРЕДАННЫМ НАЗВАНИЕМ
            Scanner dictionary = new Scanner(new File(fileName));
            //БЕРЕМ ПЕРВУЮ СТРОКУ ДАННОГО ФАЙЛА
            String currentString = dictionary.nextLine();
            //РАЗБИВАЕМ ДАННУЮ СТРОКУ НА ЭЛЕМЕНТ 0 - СЛОВО, ЭЛЕМЕНТ 1 - ЕГО ПЕРЕВОД
            String[] currentTranslation = currentString.split(" ");
            //СОЗДАЕМ ПЕРВЫЙ ЭЛЕМЕНТ, АНАЛОГИЧНО СВЯЗНОМУ СПИСКУ
            Translations newTranslation = new Translations(new Word(currentTranslation[0], currentTranslation[1]));
            head = newTranslation;
            tail = newTranslation;
            //ДОБАВЛЯЕМ ЭЛЕМЕНТЫ МЕТОДОМ INSERT == ДОБАВЛЕНИЕ ЭЛЕМЕНТА В СВЯЗНЫЙ СПИСОК, СМ НИЖЕ
            //ДО ТЕХ ПОР, ПОКА В СЧИТАННОМ ФАЙЛЕ ИМЕЕТСЯ СТРОКА
            //СЧИТЫВАЕМ ЕЕ
            while ((currentString = dictionary.nextLine()) != null) {
                //РАЗБИВАЕМ СТРОКУ, АНАЛОГИЧНО ВЫШЕ
                currentTranslation = currentString.split(" ");
                //ДОБАВЛЯЕМ СЛОВО
                insert(currentTranslation[0], currentTranslation[1]);
            }
        }
        //ЕСЛИ ПРОГРАММА НЕ МОЖЕТ НАЙТИ ФАЙЛ
        catch (FileNotFoundException e) {
            //ВЫВОДИТСЯ ИНФОРМАЦИЯ ОБ ОШИБКЕ
            System.err.println("Не прочитан файл");
            //ВЫБРАСЫВАЕТСЯ НЕОБРАБАТЫВАЕМОЕ ИСКЛЮЧЕНИЕ
            //ЧТОБЫ ПРОГРАММА НЕ РАБОТАЛА ДАЛЬШЕ
            //ИБО ЭТО БЕССМЫСЛЕННО
            throw new IllegalArgumentException(e);
        }
    }

    //МЕТОД - ПОКАЗАТЬ ВСЕ ЭЛЕМЕНТЫ СЛОВАРЯ
    public void show() {
        //БЕРЕМ КРАЙНИЙ ЭЛМЕНТ СПИСКА
        Translations current = head;
        //ДО ТЕХ ПОР ПОКА НЕ ДОЙДЕМ ДО КОНЕЧНОГО ЭЛЕМЕНТА
        //ПРОХОДИМСЯ ПО НАШЕМУ СПИСКУ
        while (current.next != null) {
            //ВЫОДИМ ЭЛЕМЕНТ СПИСКА ЧЕРЕЗ МЕТОД toString ПРОПИСАННЫЙ В Word
            System.out.println(current.value.toString());
            //БЕРЕМ СЛЕДУЮЩИЙ ЭЛЕМЕНТ
            current = current.next;
        }
        //НА NULL ТАК ЖЕ ССЫЛАЕТСЯ 1 ЭЛЕМЕНТ, ТАК ЧТО ВЫВОДИМ И ЕГО
        System.out.print(current.value.toString());
    }

    public void unique() {
        //Я НЕ ЕБУ
        while (true) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         //Я НЕ ЕБУ

        }
    }

    public void remove(String k) {
        //ПРОВЕРКА НА ДЕБИЛА. ЕСЛИ НЕТ НИ ОДНОГО ЭЛЕМЕНТА - ОБРЫВАЕМ
        if (head == null) return;
        //ЕСЛИ ЛИШЬ 1 ЭЛЕМЕНТ - СРАЗУ УДАЛЯЕМ И ЗБС
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        //ЕСЛИ САМЫЙ ВЕРХНИЙ ЭЛЕМЕНТ ИСКОМЫЙ - ПЕРЕКЛЮЧАЕМ НА СЛЕДУЮЩИЙ
        if (head.value.translation.equals(k)) {
            head = head.next;
            return;
        }

        Translations translations = head;

        //ВЕДЕМ ПОИСК ПО СПИСКУ
        while (translations != null) {
            //ЕСЛИ СЛЕДУЮЩЕЕ СЛОВО ИСКОМОЕ
            if (translations.next.value.word.equals(k)) {
                //ПРОВЕРЯЕМ, НЕ СОВПАДАЕТ ЛИ ОНО С КРАЙНИМ
                if (tail == translations.next) {
                    //ЕСЛИ СОВПАДАЕТ - ЗАМЕНЯЕМ
                    tail = translations;
                }
                //НЕ СОВПАДАЕТ - ПРОПУСКАЕМ
                translations.next = translations.next.next;
                return;
            }
            //ИЩЕМ ДАЛЬШЕ, ЕСЛИ НЕ СОВПАДАЕТ
            translations = translations.next;
        }
    }

    public int numLen1() {
        //ДЕФОЛТНОЕ ЗНАЧЕНИЕ - 0
        //МЫ НЕ ЗНАЕМ СКОЛЬКО СЛОВ БУДЕТ В СЛОВАРЕ
        //УДОВЛЕТВОРЯЮЩИХ УСЛОВИЮ
        //В ДАННОЙ СИТУАЦИИ - НАИБОЛЬШЕЕ ЧИСЛО: 0
        int count = 0;
        //БЕРЕМ ВСЕ СЛОВА
        Translations all = head;
        //ПРОХОДИМСЯ ПО ВСЕМУ СПИСКУ СЛОВ
        while (all.next != null) {
            //БЕРЕМ ДЛИНУ СТРОКИ ПЕРЕВОДА
            int value1 = all.value.getTranslation().length();
            //БЕРЕМ ДЛИНУ СТРОКИ САМОГО СЛОВА
            int value2 = all.value.getWord().length();
            //СРАВНИВАЕМ ИХ
            //ЕСЛИ ИХ ДЛИНА РАВНА ИЛИ ОТЛИЧАЕТ НЕ БОЛЕЕ ЧЕМ НА 1
            if (value1 == value2 || value1 + 1 == value2 || value1 == value2 + 1) {
                //КОЛИЧЕСТВО ТАКИХ СЛОВ УВЕЛИЧИВАЕТСЯ НА 1
                count++;
            }
        }
        //ВОЗВРАЩАЕМ ЭТО КОЛИЧЕСТВО
        return count;
    }

    //ПЕРЕВОД ТЕКСТА ПО СЛОВАРЮ
    public String translate(String text) {
        //РАЗБИВАЕМ ТЕКСТ ПО ПРОБЕЛУ
        String[] textToArr = text.split(" ");
        //ДЕВОЛТНОЕ ЗНАЧЕНИЕ - НИКАКОЕ
        String translatedText = "";
        //СНОВА БЕРЕМ ВЕСЬ СЛОВАРЬ
        Translations all = head;
        //ПРОХОДИМСЯ ПО ВСЕМУ СПИСКУ СЛОВ ИЗ ТЕКСТА, КОТОРЫЙ НАДО ПЕРЕВЕСТИ
        for (int count = 0; count < textToArr.length; count++) {
            //ИЩЕМ НУЖНОЕ НАМ ЗНАЧЕНИЕ
            while (!all.value.getWord().equals(textToArr[count])) {
                all = all.next;
            }
            //ДОБАВЛЯЕМ СЛОВО - ПЕРЕВОД И ПРОБЕЛ ДЛЯ СЛЕДУЮЩЕГО СЛОВА
            translatedText += all.value.getTranslation() + " ";
            //СНОВА ВОЗВРАЩАЕМСЯ В НАЧАЛО СПИСКА СЛОВ - ПЕРЕВОДОВ
            all = head;
        }
        //ВОЗВРАЩАЕМ ПЕРЕВЕДЕННЫЙ ТЕКСТ
        return translatedText;
    }

    //ДОБАВЛЕНИЕ ЭЛЕМЕНТА
    public void insert(String k, String v) {
        //АНАЛОГИЧНО 1-1 ТОМУ КАК ДОБАВЛЯЕМ ЭЛЕМЕНТ В СВЯЗНЫЙ СПИСОК
        Translations newTranslation = new Translations(new Word(k, v));
        tail.next = newTranslation;
        tail = newTranslation;
    }
}