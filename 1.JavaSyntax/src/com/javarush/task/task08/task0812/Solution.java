package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int last = 0;
        int current = 0;

        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(bufferedReader.readLine()));
            if (i == 0) {
                current++;
            } else if (list.get(i) == list.get(i - 1)) {
                current++;
            } else {
                if (current > last) {
                    last = current;
                }
                current = 1;
            }
        }

        if (last > current) {
            System.out.println(last);
        } else {
            System.out.println(current);
        }
    }
}