package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        ArrayList<Integer> list;
        String fileName;

        list = new ArrayList<Integer>();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        fileName = bufferedReader.readLine();
        bufferedReader.close();

        fileInputStream = new FileInputStream(fileName);

        while (fileInputStream.available() > 0) {
            int value = fileInputStream.read();
            if (!list.contains(value)) {
                list.add(value);
            }
        }

        Collections.sort(list);

        for (Integer iterator : list) {
            System.out.print(iterator + " ");
        }

        fileInputStream.close();

    }
}
