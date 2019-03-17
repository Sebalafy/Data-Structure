package com.company;

public class LinkedListStack<E> implements Stack{

    private LinkedList<Integer> list;

    public LinkedListStack(){
        list = new LinkedList<Integer>();
    }

    public int getSize(){
        return list.getSize();
    }
    public int getCapacity(){
        return list.getSize();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void push(int e)
    { list.addFirst(e);}

    public int pop(){
        return list.remove(0);
    }
    public int peek(){
        return list.getFirst();
    }

    public String toString(){
        return list.toString();
    }
}
