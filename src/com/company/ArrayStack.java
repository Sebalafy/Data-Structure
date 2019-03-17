package com.company;

public class ArrayStack implements Stack {
    Array array;

    public ArrayStack(int capacity){
        array = new Array(capacity);
    }
    public ArrayStack(){
        array = new Array();
    }
    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
    @Override
    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public void push(int e){
        array.addLast(e);
    }
    @Override
    public int pop(){
        return array.removeLast();
    }
    @Override
    public int peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        return array.toString();
    }
}
