package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner fileStream = new Scanner(new FileInputStream(reader.readLine()));

        ArrayList<Integer> list = new ArrayList<>();
        while (fileStream.hasNextLine()) {
            list.add(fileStream.nextInt());
        }

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return x.compareTo(y);
            }
        });

        for (int i = 0; i < list.size(); i++) {
            Integer check = list.get(i);
            if (check % 2 == 0) {
                System.out.println(check);
            }
        }

        fileStream.close();
        reader.close();

    }


}
