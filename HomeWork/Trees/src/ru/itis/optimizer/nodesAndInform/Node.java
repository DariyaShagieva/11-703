package ru.itis.optimizer.nodesAndInform;

/**
 * Trees
 * <p>
 * 11 04 2018
 *
 * @author Nita
 */
public class Node {
    int name;
    Node [] children;

    public Node(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }
}
