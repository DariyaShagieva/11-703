package ru.itis;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 04.04.2018
 * BinarySearchTreeImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

    private class TreeNode {
        private TreeNode parent;
        private T value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(T value) {
            this.value = value;
        }
    }

    private TreeNode root;

    public BinarySearchTreeImpl() {
        this.root = null;
    }

    @Override
    public void insert(T element) {
        this.root = insert(root, element);
    }

    private TreeNode par;

    private TreeNode insert(TreeNode root, T element) {
        if (root == null) {
            root = new TreeNode(element);
            root.parent = par;
        } else if (root.value.compareTo(element) >= 0) {
            par = root;
            root.left = insert(root.left, element);
        } else {
            par = root;
            root.right = insert(root.right, element);
        }
        return root;
    }

    @Override
    public boolean remove(T element) {
        return remove(root, element);
    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    @Override
    public void print() {
        print(this.root);
    }

    private void print(TreeNode root) {
        if (root != null) {
            print(root.left);
            System.out.print(root.value + " ");
            print(root.right);
        }
    }

    private boolean contains(TreeNode root, T element) {
        if (root == null) return false;
        else {
            if (root.value.compareTo(element) == 0) return true;
            else {
                if (root.value.compareTo(element) > 0) {
                    return contains(root.left, element);
                } else {
                    return contains(root.right, element);
                }
            }
        }
    }

    private boolean remove(TreeNode root, T element) {
        if (root == null)
            return false;
        if (element.compareTo(root.value) < 0) {
            remove(root.left, element);
        } else {
            if (element.compareTo(root.value) > 0) {
                remove(root.right, element);
            } else {
                if (root.left != null && root.right != null) {
                    TreeNode minLeft = root.right;
                    while (minLeft.left != null) {
                        minLeft = minLeft.left;
                    }
                    root.value = minLeft.value;
                    root.value = minLeft.value;
                    replace(minLeft, minLeft.right);
                } else {
                    if (root.left != null) {
                        replace(root, root.left);
                    } else {
                        if (root.right != null) {
                            replace(root, root.right);
                        } else {
                            replace(root, null);
                        }
                    }
                }
            }
        }
        return true;
    }

    private void replace(TreeNode a, TreeNode b) {
        if (a.parent == null)
            root = b;
        else if (a == a.parent.left)
            a.parent.left = b;
        else
            a.parent.right = b;
        if (b != null)
            b.parent = a.parent;
    }

    @Override
    public void printByLevels() {
        printByLevels(root);
    }

    private void printByLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode previous = null;
        do {
            System.out.print(root.value + "\t");
            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }
            previous = root;
            root = queue.poll();
            if (previous == root) {
                break;
            }
            if (newLevel(previous, root)) {
                System.out.println();
            }
            if (queue.isEmpty()) {
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            if (queue.isEmpty()) {
                System.out.println("\t" + root.value);
            }
        } while (!queue.isEmpty());

    }

    private boolean newLevel(TreeNode first, TreeNode second) {
        while (first != root && second != root) {
            first = first.parent;
            second = second.parent;
        }
        return first != second;
    }
}