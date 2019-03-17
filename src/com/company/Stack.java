package com.company;

//Stack：liner && last in first out
/*
Applications:
A1： undo操作
A2： 程序调用的系统栈
A3: 括号匹配
 */
public interface Stack {
    public int getSize();
    public int getCapacity();
    public boolean isEmpty();
    public void push(int e);
    public int pop();
    public int peek();
}

/* 括号匹配 leetcode-20

import java.util.Stack;
class Solution {
    public boolean isValid(String str)
    {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{')
            {
                s.push(c);
            }
            else {
                if (!s.isEmpty()){
                    char topchar = s.pop();
                    if (c == ')' && topchar != '(') {
                        return false;
                    }
                    if (c == ']' && topchar != '[') {
                        return false;
                    }
                    if (c == '}' && topchar != '{') {
                        return false;
                    }
                }
                else {
                    return false;
                }

            }
        }return s.isEmpty();
    }
}
 */