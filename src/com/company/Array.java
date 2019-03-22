package com.company;

//静态数组：容量固定
//EA:可以使用泛型扩展（泛型对象是类），但不支持基本数据类型（可使用包装类转换为类对象）。
//EB:可以通过创建新数组并拷贝原数据实现动态数组。
//EC：可以扩展为队列、循环队列（队空 tail == first， 队满 （tail+1）%capacity == first， 浪费一个元素空间）
public class Array
{
    // 索引可以有语意也可以没有语意
    private int index;
    private int size = 0;
    private int[] array = {0};
    private int capacity = 0;

    public Array(int capacity){
        this.array = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public Array(int[] nums){
        array = nums;
        size = nums.length;
    }

    // 直接调用其他构造函数
    public Array(){
        this(10);
    }

    public  void initArray(int[] initValue) {
        // Method A
        int[] newArrayA = {1, 2, 3, 4, 5};
        // *Error: this.array = {1, 2, 3, 4, 5};
        // Method B
        this.array = new int[10];
        // Method C
        this.array = new int[]{1, 2, 3};
        // Method D
        int[] newArrayB = new int[10];
        this.array = newArrayB;

        for (int i = 0; i < initValue.length; i++) {
            array[i] = initValue[i];
        }
        this.size = initValue.length;
    }

    public int getCapacity(){
        return array.length;
    }
    public int getSize() {
        return this.size;
    }
    public int getElement(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("getElement failed. Index is illegal.");
        return array[index];
    }
    public void setElement(int index, int e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("getElement failed. Index is illegal.");
        array[index] = e;
    }

    boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, int e) {
        if (size >= array.length){
            resize(2*array.length);
        }
        if (index > size || index < 0) {
            throw new IllegalArgumentException("add failed, Index is illegal.");
        }
        for (int i = size - 1; i >= index; i--){
            array[i+1] = array[i];
        }
        array[index] = e;
        size++;
    }
    public void addLast(int e) {
        add(size, e);
    }
    public void addFirst(int e) {
        add(0, e);
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array size = %d, capacity = %d\n", size, getCapacity()));
        res.append("[");
        for (int i = 0; i < size; i++){
            res.append(array[i]);
            if (size - 1 != i) {
                res.append(", ");
            }
        }
        res.append("]\n");
        return res.toString();
    }

    public boolean contains(int e) {
        for (int i = 0; i < size; i++){
            if (array[i] == e){
                return true;
            }
        }
        return false;
    }
    public int find(int e) {
        for (int i = 0; i < size; i++){
            if (array[i] == e){
                return i;
            }
        }
        return -1;
    }

    public int remove(int index){
        if (index > size || index < 0) {
            throw new IllegalArgumentException("add failed, Index is illegal.");
        }
        int data = array[index];
        for (int i = index + 1; i  < size; i++) {
            array[i - 1] = array[i];
        }
        // 不用考虑 index == size - 1 的情况，因为size--，访问不到。
        size--;
        return data;
    }
    public int getLast(){
        if (size == 0)
            throw new IllegalArgumentException("GetLast Failed. EmptyArray");
        return array[size-1];
    }

    public void removeElement(int e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }
    public int removeLast(){
        return remove(size-1);
    }

    public void resize(int newcapacity){
        int[] newArray = new int[newcapacity];
        if (newcapacity <= array.length ){
            throw new IllegalArgumentException("resize failed. Illegal capacity.");
        }
        for (int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }


}

//EA：泛型扩展
/*
class Array<T> {
    private T[] array;
    ...
    array = (T[])new Object[capacity];
    ...
    public T getElement(int index){...}
    public void add(int index, T e){...}
    ...
    e == array[i]   --->  (T) e.equals(array[i]);
    ..
    public void remove(int index){ ...; array[i] == null; //loitering objects ...}
}
 */

//EB：动态数组
/*
    //original array
    Array array = new Array(10);
    array.initArray(new int[]{1,2,3,4,5,6,7,8,9,10});

    //计算带 resize的addLast的时间复杂度，因为不会每次都resize，可将resize均摊到多个addLast，假设capacity为10，addLast10次后扩容，需要10+10+1，均摊为21/10,为O（1）
    //同时可能造成复杂度震荡：在capacity边界不断增加一个元素、删除一个元素，可采用lazy策略，capacity变为2capacity后，size为1/2capacity时才减半2capacity,注意capacity可能在逐渐变小至1。
    private void resize(int capacity){ // Java collections 中 ArrayList实际上为1.5倍扩容
         int[] newArray = new int[capacity];
         // T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }
 */
