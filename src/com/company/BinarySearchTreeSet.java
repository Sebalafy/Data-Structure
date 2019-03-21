package com.company;

// java.util.TreeSet 基于平衡二叉树：红黑树实现
//将单词存入set，如果bst不存重复单词，则可统计用了多少不同单词。
public class BinarySearchTreeSet <E extends Comparable<E>>  implements  Set<E>{

    private BinarySearchTree<E> bst;

    public BinarySearchTreeSet(){
        bst = new BinarySearchTree<>();
    }

    @Override
    public void add(E e){
        bst.add(e);
    }
    @Override
    public void remove(E e){
        bst.remove(e);
    }
    @Override
    public boolean isEmpty(){
        return bst.isEmpty();
    }
    @Override
    public boolean contains(E e){
        return bst.contains(e);
    }
    @Override
    public int getSize(){
        return bst.getSize();
    }

}
