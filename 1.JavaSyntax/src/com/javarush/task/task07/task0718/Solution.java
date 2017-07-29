package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int elem = -1;
        for (int i = 0; i < 10; i++) {
            list.add(bufferedReader.readLine());
            if (i > 0 && elem < 0 && (list.get(i)).length() < (list.get(i - 1).length())) {
                elem = i;
            }
        }
        if (elem > 0) {
            System.out.println((elem));
        }
    }
}

