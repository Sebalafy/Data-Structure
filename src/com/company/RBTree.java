package com.company;



public class RBTree<K extends Comparable<K>, V > implements  Map<K, V>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            //由于新节点首先将与其他节点融合，因此新节点默认为RED，后续可做其他操作。
            this.color = RED;
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
    //向红黑树中添加新元素并返回红黑树的根
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;
    }

    //对node进行左旋操作并返回当前子树的根节点
    private Node leftRotate(Node node){
        Node rc = node.right;
        node.right = rc.left;
        rc.left = node;
        //!!注意节点颜色的变化情况，根节点颜色不变！
        rc.color = node.color;
        node.color = RED;
        return rc;
    }

    //颜色翻转:根节点变红，两个子节点变黑
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node rightRotate(Node node){
        Node lc = node.left;
        node.left = lc.right;
        lc.right = node;
        //!!注意节点颜色的变化情况，根节点颜色不变！
        lc.color = node.color;
        node.color = RED;

        return lc;
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

        //根据当前节点颜色以及子节点颜色的情况决定是否旋转

        /*
                黑
              /
            红 （当前节点）
             \
             红
         */
        if (node.right.color == RED && node.left.color != RED){
            node = leftRotate(node);
        }

        /*
                黑 （当前节点）
              /
            红
           /
        红
         */

        if (node.left.color == RED && node.left.left.color == RED){
            node = rightRotate(node);
        }

        if (node.left.color == RED && node.right.color == RED){
            flipColors(node);
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
