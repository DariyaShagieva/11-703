package ru.itis.search.algoritm.algoritmLee.as;

import ru.itis.search.algoritm.Searcher;

/**
 * AlgoritmLi
 * <p>
 * 26 03 2018
 *
 * @author Nita
 */
public class LeeSearcher {
    public static int[][] placeFounder(int position, int[][] matrix) {
        int[][] pos = new int[0][2];
        int num = 1;
        for (int c = 0; c < matrix.length; c++) {
            for (int count = 0; count < matrix[c].length; count++) {
                if (position == matrix[c][count]) {
                    pos = merge(pos);
                    pos[num - 1] = new int[]{c, count};
                    num++;
                }
            }
        }
        return pos;
    }

    public static void showMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static int[][] merge(int[][] matrix) {
        int[][] matrixRebuild = new int[matrix.length + 1][2];
        for (int c = 0; c < matrix.length; c++) {
            matrixRebuild[c] = matrix[c];
        }
        return matrixRebuild;
    }

}
