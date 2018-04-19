package ru.itis;

import ru.itis.optimizer.TreeOptimizer;

public class Main {

    public static void main(String[] args) {
        TreeOptimizer opt = new TreeOptimizer();
        System.out.println(opt.getNumNewNodesCount());
    }
}
