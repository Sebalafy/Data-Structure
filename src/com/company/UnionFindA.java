package com.company;

//Quick Find
public class UnionFindA implements UF {
    private int[] id;

    public UnionFindA(int size){
        id = new int[size];
        for (int i = 0; i< id.length; i++){
            id[i] = i;
        }
    }

    @Override
    public int getSize(){
        return id.length;
    }

    private int find(int p){
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if (pID == qID){
            return;
        }
        for (int i = 0; i <id.length;i++){
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
