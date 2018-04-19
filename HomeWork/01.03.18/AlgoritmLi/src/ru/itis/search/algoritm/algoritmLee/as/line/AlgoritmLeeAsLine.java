package ru.itis.search.algoritm.algoritmLee.as.line;

import ru.itis.search.algoritm.Searcher;
import ru.itis.search.algoritm.algoritmLee.as.LeeSearcher;

/**
 * AlgoritmLi
 * <p>
 * 19 03 2018
 *
 * @author Nita
 */
public class AlgoritmLeeAsLine implements Searcher {
    @Override
    public int numOfAction(int[][] matrix) {
        int[][] beguine = LeeSearcher.placeFounder(1, matrix);
        int[][] end = LeeSearcher.placeFounder(-1, matrix);

        if (beguine[0][0] == -1 || end[0][0] == -1) {
            return -1;
        }

        int currentNum = 2;
        boolean equals = true;
        int[][] current = beguine;
        int count;
        do{
            count = 0;
            for (int c = 0; c < current.length; c++){
                int y = current[c][0];
                int x = current[c][1];

                if (matrix.length > y + 1 && matrix[y + 1].length > x && (matrix[y + 1][x] == 0 || matrix[y + 1][x] == -1)) {
                    matrix[y + 1][x] = currentNum;
                    count++;
                }

                if (y > 0 && matrix[y].length > x && (matrix[y - 1][x] == 0 || matrix[y - 1][x] == -1)) {
                    matrix[y - 1][x] = currentNum;
                    count++;
                }

                if (matrix[y].length > x + 1 && (matrix[y][x + 1] == 0 || matrix[y][x + 1] == -1)) {
                    matrix[y][x + 1] = currentNum;
                    count++;
                }

                if (x > 0 && (matrix[y][x - 1] == 0 || matrix[y][x - 1] == -1)) {
                    matrix[y][x - 1] = currentNum;
                    count++;
                }
            }

            if (count == 0 || matrix[end[0][0]][end[0][1]] != -1) {
                equals = false;
            }
            LeeSearcher.showMatrix(matrix);
            System.out.println();
            current = LeeSearcher.placeFounder(currentNum, matrix);
            currentNum++;
        } while (equals && matrix[end[0][0]][end[0][1]] == -1);

        return matrix[end[0][0]][end[0][1]];
    }
}
