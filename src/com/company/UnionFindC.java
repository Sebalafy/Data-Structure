package com.company;

// 基于子树节点数的优化(sz)：合并树后尽量不增加树的深度，节点总数少的合并到节点总数多的
//基于子树深度的优化（rank）：深度低的树向深度高的树合并
public class UnionFindC implements UF{
    private int[] id;
    //基于子树节点数的优化
    private int[] sz;
    //基于子树深度的优化
    private  int[] rank;

    public UnionFindC(int size){
        id = new int[size];
        sz = new int[size];
        rank = new int[size];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
            sz[i] = 1;
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

        if (sz[pRoot] < sz[qRoot]){
            // p -> q
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        /* 基于子树深度的优化
        if (rank[pRoot] > rank[qRoot]){
            id[qRoot] = pRoot;
        }else if (rank[pRoot] < rank[qRoot]){
            id[pRoot] = qRoot;
        }else {
            id[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
        */
    }

}
