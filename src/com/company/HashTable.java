package com.company;
import java.util.TreeMap;

// 哈希表性能与哈希表数组大小相关，动态变化大小可提升性能
public class HashTable <K, V> {
    // Treemap的key需要comparable
    private TreeMap<K, V>[] hashTable;
    private  int M;
    private int size;

    // size 存储元素个数， M 数组元素个数， size/N  平均每个位置存储元素个数 lowerTol < size/N  < upperTol
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    public HashTable(int M){
        this.M = M;
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashTable[i] = new TreeMap<>();
    }

    public HashTable(){this(initCapacity);}

    private int hash(K key){
        //和0x7fffffff与 == 取正数
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        }else {
            map.put(key, value);
            size++;
            if (size >= upperTol * M){
                resize(2*M);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)){
            ret = map.remove(key);
            size --;
            if (size <= lowerTol * M && M/2 > initCapacity){
                resize(M/2);
            }
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)){
            return;
        }
        map.put(key, value);
    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }
    public V get(K key){
        return hashTable[hash(key)].get(key);
    }

    private  void resize(int newM){
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        int oldM = this.M;
        this.M = newM;

        for (int i = 0; i < oldM; i++){
            TreeMap<K, V> map = hashTable[i];
            for (K key: map.keySet()){
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashTable = newHashTable;
    }
}
