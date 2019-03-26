package com.company;
import java.util.TreeMap;

public class Trie {

    private class Node{
        boolean isWord;
        TreeMap<Character, Node> next;

        Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return this.size;
    }

    public void add(String word){
        Node node = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.next.get(c) == null){
                node.next.put(c, new Node());
            }
            node = node.next.get(c);
        }
        if (!node.isWord){
            node.isWord = true;
            size++;
        }

    }

    public boolean contains(String word){
        Node node = root;
        // root 为空，work.length()次循环后，node为存储最后一个字符的节点。
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (node.next.get(c) != null){
                if (!node.next.get(c).isWord){
                    return false;
                }
                node = node.next.get(c);
            }
        }
        return node.isWord;
    }

    //Trie中是否有以prefix为前缀的单词
    public boolean isPrefix(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.next.get(c) == null) {
                return false;
            }
            node = node.next.get(c);
        }
        return true;
    }
}
