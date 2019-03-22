package com.company;

public interface Queue {
    int getSize();
    boolean isEmpty();
    int getFront();
    void enqueue(int e);
    int dequeue();
}
