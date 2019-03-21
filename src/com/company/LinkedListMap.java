package com.company;

public class LinkedListMap<K, V> implements Map<K, V> {
    class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node node){
            this.key = key;
            this.value = value;
            this.next = node;
        }

        public Node(){
            this.key = null;
            this.value = null;
            this.next = null;
        }

        public Node (K key){
            this.key = key;
            this.value = null;
            this.next = null;
        }

        @Override
        public String toString(){
            return key.toString()+":"+value.toString()+"\n";
        }
    }
    private int size;
    private Node dummyHead;

    @Override
    public int getSize(){
        return size;
    }
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    private Node getNode(K key){
        Node node = dummyHead.next;
        while (node != null){
            if (node.key.equals(key)){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key){
        Node node = getNode(key);
        return node == null;
    }

    @Override
    public V get(K key){
        Node node = getNode(key);
        return node == null ? node.value: null;
    }

    @Override
    public void add(K key,V value){
        Node node = getNode(key);

        if (node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
        } else{
            node.value = value;
        }
    }

    @Override
    public void set(K key, V value){
        Node node = getNode(key);
        if (node == null){
            return;
        }
        node.value = value;
    }

    @Override
    public V remove(K key){
        Node node = dummyHead.next;
        while (node.next != null){
            if (node.next.key.equals(key)){
                break;
            }
            node = node.next;
        }

        if (node.next != null){
            Node delNode = node.next;
            node.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }
}
