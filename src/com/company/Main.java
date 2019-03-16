package com.company;

public class Main {

    public static void main(String[] args) {
	    Array array = new Array(10);
 	    //泛型：Array<Integer> array = new Array<Integer>(10); || Array<Integer> array = new Array<>(10);
	    array.initArray(new int[]{1, 2, 4, 5, 6});
        System.out.print(array); //array.toString()
    }
}
