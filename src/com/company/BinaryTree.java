package com.company;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

// BinarySearchTree
public class BinaryTree<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }

    }

    private Node root;
    private int size;

    public BinaryTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root, e);
    }

    //向以node为根节点的树插入元素e，并返回该树的根节点
    private Node add(Node node, E e){
       if(node == null){
           size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
        }else if(e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
    }

    /*
    private void add(Node node, E e){
        if (node.e.equals(e)){
            node = new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0){
            add(node.left,e);
        }else{
            add(node.right,e);
        }
    }
    */

    public boolean contains(E e){
        return contains(root, e);
    }
    private boolean contains(Node node, E e){
        if (e.compareTo(node.e) == 0){
            return true;
        }
        if (node == null){
            return false;
        }
        if (e.compareTo(node.e) < 0){
            return  contains(node.left, e);
        } else if (e.compareTo(node.e) > 0){
            return contains(node.right, e);
        }
        return false;
    }

    public void preOrder(){
        System.out.print("\nBinary Search Tree:\n Preorder:");
        preOrder(root);

    }
    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.e+" -> ");
        preOrder(node.left);
        preOrder(node.right);

    }

    // NOT RECURSION with stack
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    // level order with queue
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.remove();
            if (node.left != null)
                q.add(node.left);
            if (node.right!=null)
                q.add(node.right);
        }
    }

    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty.");
            return minimum(root).e;
    }

    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    public E removeMin(){
         E min = minimum();
         root = removeMin(root);
         return min;
    }

    //删除以node为根节点树的最小值并返回该根节点。
    private Node removeMin(Node node){
        //无左子树，当前最小
        if (node.left == null){
            //取出右子树并返回，以该节点右子树替换该节点
            Node rnode = node.right;
            node.right = null;
            size--;
            return rnode;
        }
        //删除该节点左子树中最小值并返回左子树
        node.left = removeMin(node.left);
        return node;
    }

    //删除任意节点，用其右子树最小节点（后继节点）替换该节点。（也可用前驱）
    public void remove(E e){
        remove(root, e);
    }

    private Node remove(Node node, E e){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }else{
            if (node.left == null){
                Node rnode = node.right;
                node.right = null;
                size--;
                return rnode;
            }
            if (node.right == null){
                Node rnode = node.left;
                node.left = null;
                size--;
                return rnode;
            }

            Node successor = minimum(node.right);
            //removeMin 中已经size--；
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
            }


    }

}
