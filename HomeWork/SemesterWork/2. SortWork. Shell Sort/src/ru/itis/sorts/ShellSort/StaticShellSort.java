package ru.itis.sorts.ShellSort;

/**
 * 2. SortWork
 *
 * 12 03 2018
 *
 * @author Nita
 */

public class StaticShellSort {
    private static ShellSort sort;
    private static int numOfAction;
    private static double timeOfAction;

    public static int[] toSort(int[] toSortArr) {
        sort = new ShellSort();
        toSortArr = sort.sort(toSortArr);
        setNumOfAction(sort.getNum());
        setTimeOfAction(sort.getTime());
        return toSortArr;
    }

    public static double getTimeOfAction() {
        return timeOfAction;
    }

    private static void setTimeOfAction(double timeOfAction) {
        StaticShellSort.timeOfAction = timeOfAction;
    }

    public static int getNumOfAction() {
        return numOfAction;
    }

    private static void setNumOfAction(int numOfAction) {
        StaticShellSort.numOfAction = numOfAction;
    }
}
