package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreFounder {

    public int finalScore() throws FileNotFoundException {
//		Считываем файл "Target"
        Scanner file = new Scanner(new File("Target"));
        String next;

//		Обнуляем переменную, которая будет хранить информацию о сумме очков
        int score = 0;

//		Первое число в файле "Target" - количество кругов. Мы его считываем и сразу создаем массив кругов мишени
        Ring[] rings = new Ring[Integer.parseInt(file.nextLine())];
//		заполняем информацию о радиусах. Она хранится так: первое число - радиус от центра
//		второе - баллы за радиус
        for (int count = 0; count < rings.length; count++) {
//			каждый раз сохраняем данные строки, для удобства использования
            next = file.nextLine();
//			каждый раз создаем новый "круг"
            rings[count] = new Ring(Integer.parseInt(next.split(" ")[0]),
                    Integer.parseInt(next.split(" ")[1]));
        }

//		считываем выстрелы аналогично кругам. Разница лишь в том, что они хранятся через ", ", а не просто " "
        Shoot[] shoots = new Shoot[Integer.parseInt(file.nextLine())];
        for (int count = 0; count < shoots.length; count++) {
            next = file.nextLine();
            shoots[count] = new Shoot(Integer.parseInt(next.split(", ")[0]),
                    Integer.parseInt(next.split(", ")[1]));
        }

//		Проходимся по массиву выстрелов (выстрелов может быть больше чем мишеней и наоборот)
        for (int count = 0; count < shoots.length; count++) {
//			т.к. у нас баллы отсортированы по убыванию - можно не волноваться. Иначе пришлось бы сделать сортировку.
//          Мы смотрим наименьшее число,
//			расстояние от центра которого меньше, чем радиус
            for (int inCount = 0; inCount < rings.length; inCount++) {
                if (shoots[count].getLength() < rings[inCount].getRadius()) {
//					Найдя такое - забираем баллы
                    score += rings[inCount].getScore();
//					И ОБЯЗАТЕЛЬНО ОБРЫВАЕМ ЦИКЛ - ИНАЧЕ ОН ПОЙДЕТ ДАЛЬШЕ
//					И БУДЕТ ПЛЮСОВАТЬ ВСЕ ПОСЛЕДУЮЩИЕ ЗНАЧЕНИЯ
                    break;
                }
            }
        }

//		Ну и возращаем результат
        return score;
    }
}
