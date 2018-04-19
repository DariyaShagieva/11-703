package ru.itis.sorts.ShellSort;

import ru.itis.sorts.Sorts;


/**
 * 2. SortWork
 *
 * 12 03 2018
 *
 * @author Nita
 */
public class ShellSort implements Sorts {

    private int num;
    private double time;

    ShellSort(){
        num = 0;
        time = 0;
    }

    @Override
    public int[] sort(int[] toSort) {
        int length = toSort.length;
        int crossValue;
        int inCount;
        int outCount;
        int count;
        time = System.nanoTime();
        for (outCount = length/2; outCount > 0; outCount/=2){
            for (count = outCount; count < length; count++){
                crossValue = toSort[count];
                for (inCount = count; inCount >= outCount; inCount -= outCount){
                    //мы ищем момент, когда кроссовое значение будет < , чем
                    //следующее за ним. до этого момента смещаем все значения
                    //сравниваем всегда элементы на расстоянии
                    //outCount
                    if (crossValue < toSort [inCount - outCount]){
                        toSort[inCount] = toSort[inCount - outCount];
                        num++;
                    } else break;
                }
                //вставляем на нужное место
                toSort[inCount] = crossValue;
            }
        }
        time = System.nanoTime() - time;

        return toSort;
    }

    public int getNum() {
        return num;
    }

    public double getTime() {
        return time;
    }
}
