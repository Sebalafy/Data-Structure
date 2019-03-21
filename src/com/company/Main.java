package com.company;

import com.sun.jdi.InterfaceType;

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

        //BinarySearchTree
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums= {1,2,3,4,5,7,8,4,3};
        for (int num: nums){
            bst.add(num);
        }
        bst.preOrder();

        //Set
        /*
        //函数参数可以传入接口类型（Set），实际使用传入实现了接口类型的类(BSTSet)
        private static double testCase(Set<String> set, String filename){
            ...
        }

        double runtime = testCase(BinarySearchTreeSet<String> set, filename);
        double runtime = testCase(LinkedListSet<String> set, filename);
         */

        //Map
        BinarySearchTreeMap<Integer, Integer> bstmap = new BinarySearchTreeMap<>();
        bstmap.add(1,1);
        bstmap.add(2,22);
        bstmap.add(3,333);
        bstmap.remove(2);
        System.out.print(bstmap);

    }
}
