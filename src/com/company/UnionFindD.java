package com.company;

public class UnionFindD implements UF {
    private int[] id;
    private  int[] rank;

    public UnionFindD(int size){
        id = new int[size];

        rank = new int[size];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return id.length;
    }

    //   O(h)
    private int find(int p){
        while( p != id[p] ){
            //id[p]存储的是p的父节点的索引，"认爷作父"
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        if (rank[pRoot] > rank[qRoot]){
            id[qRoot] = pRoot;
        }else if (rank[pRoot] < rank[qRoot]){
            id[pRoot] = qRoot;
        }else {
            id[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
