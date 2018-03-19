package ru.itis.search.algoritm.algoritmLee.as.wave;

import ru.itis.search.algoritm.Searcher;

/**
 * AlgoritmLee
 * <p>
 * 19 03 2018
 *
 * @author Nita
 */
public class AlgorithmLeeSearcherAsWave implements Searcher {

    // 0 - пустые клетки
    // 1 - точка начала
    // -1 - точка окончания
    // -2 - недосягаемые точки

    @Override
    public int numOfAction(int[][] matrix) {
        int[][] beguine = placeFounder(1, matrix);
        int[][] end = placeFounder(-1, matrix);

        //случай, когда нет начальной или конечной позиции
        if (beguine[0][0] == -1 || end[0][0] == -1) {
            return -1;
        }

        int currentNum = 2;
        boolean equals = true;
        int[][] current = beguine;
        int count;
        do {
            count = 0;
            for (int c = 0; c < current.length; c++) {
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

                if (matrix.length > y + 1 && matrix[y + 1].length > x + 1 && (matrix[y + 1][x + 1] == 0 || matrix[y + 1][x + 1] == -1)) {
                    matrix[y + 1][x + 1] = currentNum;
                    count++;
                }

                if (x > 0 && matrix.length > y + 1 && matrix[y + 1].length > x - 1 && (matrix[y + 1][x - 1] == 0 || matrix[y + 1][x - 1] == -1)) {
                    matrix[y + 1][x - 1] = currentNum;
                    count++;
                }

                if (y > 0 && matrix[y - 1].length > x + 1 && matrix[y - 1][x + 1] == 0) {
                    matrix[y - 1][x + 1] = currentNum;
                    count++;
                }

                if (y > 0 && matrix[y - 1].length > x - 1 && x > 0 && (matrix[y - 1][x - 1] == 0 || matrix[y - 1][x - 1] == -1)) {
                    matrix[y - 1][x - 1] = currentNum;
                    count++;
                }
            }

            if (count == 0 || matrix[end[0][0]][end[0][1]] != -1) {
                equals = false;
            }

            showMatrix(matrix);
            System.out.println();
            current = placeFounder(currentNum, matrix);
            currentNum++;

        } while (equals && matrix[end[0][0]][end[0][1]] == -1);
        return matrix[end[0][0]][end[0][1]];
    }

    private int[][] placeFounder(int position, int[][] matrix) {
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

    private void showMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] merge(int[][] matrix) {
        int[][] matrixRebuild = new int[matrix.length + 1][2];
        for (int c = 0; c < matrix.length; c++) {
            matrixRebuild[c] = matrix[c];
        }
        return matrixRebuild;
    }
}
