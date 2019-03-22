package com.company;

public class PriorityQueue implements Queue {
    private MaxHeap maxHeap;
    public PriorityQueue(){
        maxHeap = new MaxHeap();
    }
    @Override
    public int getSize(){
        return maxHeap.getSize();
    }
    @Override
    public boolean isEmpty(){
        return maxHeap.isEmpty();
    }

    @Override
    public int getFront(){
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(int e){
        maxHeap.add(e);
    }

    @Override
    public int dequeue(){
        return maxHeap.extractMax();
    }

}
