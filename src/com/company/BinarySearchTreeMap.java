package com.company;

public class BinarySearchTreeMap<K extends Comparable<K>, V > implements  Map<K, V>{
    class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString(){
            return key.toString()+":"+value.toString()+"\n";
        }
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
        return node;
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
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0){
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
            node.right = successor.right;
            successor.left = successor.right = null;
        }else if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
        }else {
            node.right = remove(node.right, key);
        }

        return node;
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
