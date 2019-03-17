package com.company;

public class Main {

    public static void main(String[] args) {
        //Array
	    Array array = new Array(10);
 	    //泛型：Array<Integer> array = new Array<Integer>(10); || Array<Integer> array = new Array<>(10);
	    array.initArray(new int[]{1, 2, 4, 5, 6});
        System.out.print(array); //array.toString()

        //ArrayStack
        ArrayStack as = new ArrayStack();
        as.push(1);
        as.push(3);
        as.push(5);
        System.out.print(as);

        //Linkedlist
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addFirst(1);
        ll.add(1,22);
        ll.addLast(333);
        ll.remove(2);
        System.out.print(ll);

        //LinkedListStack
        LinkedListStack<Integer> lls = new LinkedListStack<>();
        lls.push(1);
        lls.push(22);
        lls.push(333);
        lls.pop();
        System.out.print(lls);



    }
}
