package ru.itis.optimizer;

import ru.itis.optimizer.nodesAndInform.ParentAndChildrens;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Trees
 * <p>
 * 11 04 2018
 *
 * @author Nita
 */
public class TreeOptimizer {

    int child;
    int nodesNum;
    ArrayList<ParentAndChildrens> parentAndChildrens;

    public TreeOptimizer() {
        try {
            ParentAndChildrens current;
            Scanner file = new Scanner(new File("input.txt"));
            String currentLine = file.nextLine();
            child = Integer.parseInt(currentLine.split(" ")[1]);
            nodesNum = Integer.parseInt(currentLine.split(" ")[0]);
            parentAndChildrens = new ArrayList<>();
            int parentInt;
            boolean added;
            for (int c = 1; c < nodesNum; c++) {
                added = false;
                parentInt = Integer.parseInt(file.nextLine());
                for (ParentAndChildrens parent : parentAndChildrens) {
                    if (parent.getParentName() == parentInt) {
                        parent.addChild(c);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    current = new ParentAndChildrens(parentInt);
                    current.addChild(c);
                    parentAndChildrens.add(current);
                }
            }
            Collections.sort(parentAndChildrens);
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка чтения файла");
            throw new IllegalStateException();
        }
    }

    private int foundMinNumOfLvls(int currentChildrenNeeds, int currentLVL) {
        int currentGenerate = (int) Math.pow(child, currentLVL);
        while (currentGenerate * child <= currentChildrenNeeds) {
            currentLVL++;
            currentGenerate *= child;
        }
        if (currentChildrenNeeds > currentGenerate){
            currentLVL++;
        }
        return currentLVL;
    }


    public int getNumNewNodesCount() {
        int currentLVL = 1;
        int count;
        int sum = 0;
        int currentSum = 0;
        for (ParentAndChildrens pac : parentAndChildrens) {
            count = foundMinNumOfLvls(pac.getChildren().size(), currentLVL);
            for (int c = currentLVL; c < count - 1; c++) {
                currentSum += Math.pow(child, c);
            }
            sum += currentSum;
            if (count != 1) {
                if (Math.pow(child, count - 1) >= pac.getChildren().size() - currentSum){
                    sum += (pac.getChildren().size() - currentSum)/child;
                }
                else sum += Math.pow(child, count - 1);
            }
            currentLVL = count;
        }
        return sum;
    }

//    public void createOptTree(){
//
//    }
//        Node [] nodes;
//
//    private ArrayList <Integer> getChilds(int num, ArrayList <ParentAndChildrens> parentAndChildrens){
//        for (ParentAndChildrens parent : parentAndChildrens){
//            if (parent.getParentName() == num){
//                return parent.getChildren();
//            }
//        }
//        return null;
//    }
}
