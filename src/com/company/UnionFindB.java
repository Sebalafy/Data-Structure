package com.company;

public class UnionFindB implements UF {
    private int[] id;

    public UnionFindB(int size){
        id = new int[size];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }

    @Override
    public int getSize(){
        return id.length;
    }

    //   O(h)
    private int find(int p){
        while( p != id[p] ){
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        id[pRoot] = qRoot;
    }

}
