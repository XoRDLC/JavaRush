package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int min = 0;
        int max = 0;
        int val = 0;
        for (int i = 0; i < 10; i++) {
            list.add(bufferedReader.readLine());
            val = (list.get(i)).length();
            if (i == 0) {
                min = 0;
                max = 0;
            } else {
                if (val < (list.get(min)).length()) {
                    min = i;
                }
                if (val > (list.get(max)).length()) {
                    max = i;
                }
            }
        }
        if (min < max) {
            System.out.println(list.get(min));
        } else {
            System.out.println(list.get(max));
        }
    }
}
