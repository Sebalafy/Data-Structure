package com.company;

//队列：实现添加头尾节点、头结点dequeue，尾节点enqueue
/*
public void enqueue(E e){
    if(list.isEmpty()){
        tail = new Node(e);
        head = tail;
    }else{
        tail.next = new Node(e);
        tail = tail.next;
    }
    size++;
}
public void dequeue(){
    if (list.isEmpty()){
        return;
    }else{
        Node node = head;
        head = head.next;
        node.next = null;
        if (head == null){
            tail = null;
        }
       size--;
    }

}
 */
public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this.e = e;
        }
        public Node(Node next){
            this.next = next;
        }

    }


    private  Node dummyHead;
    private int size;
    public int getSize(){
        return size;
    }
    public LinkedList(){
        dummyHead = new Node(null, null);
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        //带虚拟头结点
        Node node = dummyHead;
        for (int i = 0; i <index; i++){
            node = node.next;
        }
        Node newNode = new Node(e, node.next);
        node.next = newNode;

        /*
        //不带虚拟头结点,需要多一次判断
        Node node = head;
        for (int i = 0; i < index - 1; i++){
            node = node.next;
        }
        Node newNode = new Node(e, node.next);
        node.next = newNode;

        if (index == 0){
            head = node;
        }
        */
        size++;
    }

    public void addLast(E e){
        add(size - 1, e);
    }

    public E get(int index){
        indexCheck(index);
        Node node = dummyHead;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        return node.e;
    }

    public void indexCheck(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Failed. Illegal Index.");
        }
    }
    public E getFirst(){
        return get(0);
    }
    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e){
        indexCheck(index);
        Node node = dummyHead;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        node.e  = e;
    }

    public E remove(int index){
        indexCheck(index);
        Node node = dummyHead;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        Node delNode = node.next;
        node.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nLinkedList:\n");
        for (Node node = dummyHead.next; node != null; node = node.next){
            sb.append(node.e+"->");
        }
        sb.append("null");
        return sb.toString();
    }

}
