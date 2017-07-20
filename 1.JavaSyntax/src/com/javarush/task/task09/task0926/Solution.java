package com.javarush.task.task09.task0926;

import java.util.ArrayList;

/* 
Список из массивов чисел
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList() {
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(new int[]{5, 4, 3, 2, 1});
        arr.add(new int[]{5, 4});
        arr.add(new int[]{5, 4, 3, 2});
        arr.add(new int[]{7, 6, 5, 4, 3, 2, 1});
        arr.add(new int[]{});
        return arr;
    }


    public static void printList(ArrayList<int[]> list) {
        for (int[] array : list) {
            for (int x : array) {
                System.out.println(x);
            }
        }
    }
}
