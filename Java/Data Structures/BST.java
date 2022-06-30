package Ex2;

import java.util.*;
public class BST {
    private BTNode<Integer> root;
    public int element;

    public BST() {
        root = null;
    }

    public boolean find(Integer i) {
        BTNode<Integer> n = root;
        boolean found = false;

        while (n != null && !found) {
            int comp = i.compareTo(n.data);
            if (comp == 0)
                found = true;
            else if (comp < 0)
                n = n.left;
            else
                n = n.right;
        }

        return found;
    }

    public boolean insert(Integer i) {
        BTNode<Integer> parent = root, child = root;
        boolean goneLeft = false;

        while (child != null && i.compareTo(child.data) != 0) {
            parent = child;
            if (i.compareTo(child.data) < 0) {
                child = child.left;
                goneLeft = true;
            } else {
                child = child.right;
                goneLeft = false;
            }
        }

        if (child != null)
            return false;  // number already present
        else {
            BTNode<Integer> leaf = new BTNode<Integer>(i);
            if (parent == null) // tree was empty
                root = leaf;
            else if (goneLeft)
                parent.left = leaf;
            else
                parent.right = leaf;
            return true;
        }
    }



    /*
     * Greater Method
     * use countGreaterNodes Methods to determine the number
     * of occurences  in BST
     */


    public int greater(int n) {
        if (root == null)
            return 0;
        return greaterNumCount(n, 0, root);
    }


    /**
     *
     * Finding the greater numbers then n
     * going through in the entire  BST
     * and return back to BST
     */
    private int  greaterNumCount(int NodeNum, int counter, BTNode Node) {

        if (Node.left != null)
            counter =  greaterNumCount(NodeNum, counter, Node.left);  //
        if (Node.right != null)
            counter =  greaterNumCount(NodeNum, counter,Node.right);
        if ((int) Node.data > NodeNum)  // if greater then n increasing counter
            counter++;

        return counter;
    }

    /**
     *Method nth()
     * deteremine the node point if not available
     * throws excetions
     */
    public int nth(int n) {
        if (root == null)
            throw new NoSuchElementException("BST not exist ");

        int nth = findElement(n, 0, root); // if finds then passed
        if ( element != -1) {
            nth =  element;
        }

        if (nth < 0 ) {   // used when the number is not found
            throw new NoSuchElementException("element is not available");
        }
        return nth;
    }


    /**
     * FindElement Method checks for num
     * if not available returns -1
     * which is used in the above fuction
     */

    public int findElement(int NodeNum, int answer , BTNode node) {
        ArrayList<Integer> nodeList = new ArrayList();
        if (node.left != null)
            answer  = findElement(NodeNum, answer , node.left);
        if (answer  == NodeNum) {
            element = (int) node.data;
            nodeList.add((int) node.data);
            return (int) node.data;
        }
        else answer ++;

        if (node.right != null)
            answer  = findElement(NodeNum, answer , node.right);

        if (node == root){

           return  -1;
    }
         else return answer;
    }

}

// End of the Class

class BTNode<T> {
    T data;
    BTNode<T> left, right;

    BTNode(T o) {
        data = o;
        left = right = null;
    }

}

