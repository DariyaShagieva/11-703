package ru.itis.optimizer.nodesAndInform;

import java.util.ArrayList;

/**
 * Trees
 * <p>
 * 11 04 2018
 *
 * @author Nita
 */
public class ParentAndChildrens implements Comparable {
    int parentName;
    ArrayList <Integer> children;

    public ParentAndChildrens(int parentName) {
        this.parentName = parentName;
        children = new ArrayList<>();
    }

    public int getParentName() {
        return parentName;
    }

    public ArrayList<Integer> getChildren() {
        return children;
    }

    public void addChild(int child){
        children.add(child);
    }


    @Override
    public int compareTo(Object o) {
        ParentAndChildrens cur = (ParentAndChildrens)o;
        for (int child : children){
            if (cur.parentName == child) return -1;
        }
        return 1;
    }
}
