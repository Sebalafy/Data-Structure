package com.company;

public class SegmentTree<E>{

    private E[] array;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        array = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length;i++)
            array[i] = arr[i];

        tree = (E[]) new Object[4*arr.length];
        buildSegmentTree(0, 0, arr.length-1);
    }

    //在treeIndex的位置创建区间left到right的线段树
    public void buildSegmentTree(int treeIndex, int left, int right){
        if (left == right ) {
            tree[treeIndex] = array[left];
            return;
        }
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        int mid = left + (right -left)/2; // 防止整型溢出：(l+r)特别大

        buildSegmentTree(leftChild, left, mid);
        buildSegmentTree(rightChild, mid+1,right);

        //根据任务设定值（max/sum）
        tree[treeIndex] =  merger.merge(tree[leftChild], tree[rightChild]);

    }
    public E get(int index){
        checkIndex(index);
        return array[index];
    }

    public int getSize(){
        return array.length;
    }
    public void checkIndex(int index){
        if (index < 0 || index >=  array.length){
            throw new IllegalArgumentException("Illegal Index");
        }
    }

    private int leftChild(int index){
        return 2*index+1;
    }
    private int rightChild(int index){
        return 2*index+2;
    }

    public E query(int qL, int qR){
        if (qL < 0 || qR < 0 || qL > array.length || qR > array.length || qR < qL){
            throw new IllegalArgumentException("Boundary Error.");
        }
        return query(0, 0, array.length, qL, qR);
    }

    // 在treeIndex为根的树中l...r 范围内查询ql...qR
    private E query(int treeIndex, int l ,int r, int qL, int qR){
        if (l == qL && r == qR){
            return tree[treeIndex];
        }
        int mid = l + (r - l)/2;
        if (qR <= mid){
            return query(leftChild(treeIndex), l, mid, qL, qR);
        }else if (qL >= mid+1){
            return query(rightChild(treeIndex), mid+1, r, qL, qR);
        }
        return merger.merge(query(leftChild(treeIndex), l, mid, qL, mid), query(rightChild(treeIndex), mid+1, r, mid+1, qR));

    }

    public void set(int index, E e){
        checkIndex(index);
        array[index] = e;
        set(0, 0, array.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e){
        if (l == r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r -l)/2;
        if (index <= mid){
            set(leftChild(treeIndex), l, mid, index, e);
        }else if(index > mid){
            set(rightChild(treeIndex), mid+1, r, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);

    }
}
