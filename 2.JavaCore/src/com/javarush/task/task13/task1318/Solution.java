package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();

        Scanner input = new Scanner(new FileInputStream(fileName));
        while (input.hasNextLine()) {
            String data = input.nextLine();
            System.out.println(data);
        }

        input.close();
        bufferedReader.close();
    }
}