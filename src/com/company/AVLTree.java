package com.company;

import java.util.ArrayList;


// Extension: 基于avl树的集合和映射
public class AVLTree<K extends Comparable<K>, V > implements  Map<K, V>{
    class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }

        @Override
        public String toString(){
            return key.toString()+":"+value.toString()+"\n";
        }
    }

    public AVLTree(){
        root =null;
        size  = 0;
    }

    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i  =1; i < keys.size(); i++){
            if (keys.get(i -1 ).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }
    public void inOrder(Node node, ArrayList<K> keys){
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced(Node node){
        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private int size;
    private Node root;
    @Override
    public int getSize(){
        return size;
    }
    @Override
    public boolean isEmpty(){return size == 0;}

    @Override
    public void add(K key, V value){
        root = add(root, key, value);
    }
    //返回添加节点后树的根节点，涉及到节点的替换： node.left = add(node.left, key, value);
    private Node add(Node node, K key, V value){
        if (node == null){
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else if (key.compareTo(node.key) > 0){
            node.right =  add(node.right, key, value);
        }else {
            node.value = value;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //如果当前节点balance factor的绝对值超出1且左子节点的左子树较高 （LL）
        if (Math.abs(getBalanceFactor(node))> 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        if (Math.abs(getBalanceFactor(node))< -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        //如果当前节点balance factor的绝对值超出1且左子节点的右子树较高 （LR）
        if (Math.abs(getBalanceFactor(node))>  1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (Math.abs(getBalanceFactor(node))< -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //对以node为根节点的子树进行右旋返回操作后的根节点
    private Node rightRotate(Node node){
        Node lc = node.left;
        Node lcrc = lc.right;
        //右旋
        lc.right = node;
        node.left = lcrc;
        //更新 height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        lc.height = Math.max(getHeight(lc.left), getHeight(lc.right)) + 1;
        return  lc;
    }

    private Node leftRotate(Node node){
        Node rc = node.right;
        Node rclc = rc.left;
        //左旋
        rc.left = node;
        node.right = rclc;
        //更新 height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        rc.height = Math.max(getHeight(rc.left), getHeight(rc.right)) + 1;
        return  rc;
    }

    //根据key在以node为根的树中寻找节点并返回该节点，直接返回节点就可以：return getNode(node.left, key);
    private Node getNode(Node node, K key){
        if (node == null){
            return null;
        }

        if (node.key.compareTo(key) > 0){
            return getNode(node.left, key);
        }else if (node.key.compareTo(key) < 0){
            return getNode(node.right, key);
        }

        return  node;
    }

    @Override
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key){
        return getNode(root, key).value;
    }

    @Override
    public void set(K key, V value){
        Node node = getNode(root, key);
        if (node == null){
            return;
        }
        node.value = value;
    }

    @Override
    public V remove(K key){
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }
    private Node remove(Node node,K key){
        if (node == null)
            return null;
        Node retNode = new Node(null, null);
        if (key.compareTo(node.key) == 0){
            //与之前相比，现在不直接return节点，三种情况互斥
            if (node.left == null){
                Node rnode = node.right;
                node.right = null;
                size--;
                retNode = rnode;
            }else if (node.right == null){
                Node rnode = node.left;
                node.left = null;
                size--;
                retNode =  rnode;
            } else {
                Node successor = minimum(node.right);
                //removeMin 中已经size--；
                //removeMin可能破坏平衡性
                successor.right = remove(node.right, successor.key);
                node.right = successor.right;
                successor.left = successor.right = null;

                retNode = successor;
            }


        }else if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }else {
            node.right = remove(node.right, key);
            retNode = node;
        }

        if (retNode == null)
            return null;

        retNode.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        //如果当前节点balance factor的绝对值超出1且左子节点的左子树较高 （LL）
        if (Math.abs(getBalanceFactor(node))> 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
        if (Math.abs(getBalanceFactor(retNode))< -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);
        //如果当前节点balance factor的绝对值超出1且左子节点的右子树较高 （LR）
        if (Math.abs(getBalanceFactor(retNode))>  1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        if (Math.abs(getBalanceFactor(retNode))< -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }


        return retNode;
    }
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
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

}
