package com.company;

public class MaxHeap {
    private Array array = null;

    public MaxHeap(int capacity){
        array = new Array(capacity);
    }

    public MaxHeap(){
        array = new Array();
    }

    public int getSize(){
        return array.getSize();
    }

    public boolean isEmpty(){
        return  array.isEmpty();
    }

    private int parent(int index){
        if (index == 0 ){
            throw new IllegalArgumentException("Index error");
        }
        return (index - 1)/2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(int e){
        array.addLast(e);
        siftUp(array.getSize() - 1);
    }

    private void siftUp(int index){
        while(index > 0 ){
            if (array.getElement(parent(index)) < array.getElement(index)){
                swap(parent(index), index);
                index = parent(index);
            }else{
                break;
            }

        }
    }

    private void swap(int indexA, int indexB){
        if (indexA < 0 || indexB < 0 || indexA > array.getSize()|| indexB > array.getSize()){
            return;
        }
        int tmp = array.getElement(indexA);
        array.setElement(indexA, array.getElement(indexB));
        array.setElement(indexB, tmp);
    }

    //将最大值取出并用末尾节点替换，删除末尾节点之后使用siftdown将当前头节点下沉至合适位置
    public int extractMax(){
        int max = findMax();
        swap(0, array.getSize() - 1);
        array.removeLast();
        siftDown(0);
        return max;
    }

    public int findMax(){
        if (array.getSize() == 0){
            throw new IllegalArgumentException("Empty heap.");
        }
        return array.getElement(0);
    }


    private void siftDown(int index){
        while(leftChild(index) < array.getSize()){
            int left = leftChild(index);
            if (left+ 1 < array.getSize() && array.getElement(left + 1)> array.getElement(left)){
                swap(index, left+1);
                index = left + 1;
            }else if (array.getElement(left)> array.getElement(index)){
                swap(index, left);
                index = left;
            }else{
                break;
            }
        }
    }

    public int repalce(int e){
        int max = extractMax();
        array.setElement(0, e);
        siftDown(0);
        return max;
    }

    public void heapify(int[] nums){
        array = new Array(nums);
        int index = (array.getSize() - 1) / 2;
        while(index >= 0){
            siftDown(index);
            index = index - 1;
        }
    }

}
