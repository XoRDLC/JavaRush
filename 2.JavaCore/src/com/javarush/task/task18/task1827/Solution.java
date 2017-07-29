package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        String productName = ""; //название товара, 30 chars (60 bytes).
        String price = ""; //цена, 8 символов.
        String quantity = ""; //количество, 4 символа.
        String fileName = "";
        if (args[0].toLowerCase().equals("-c")) {
            productName = args[1];
            price = args[2];
            quantity = args[3];
        }


        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        fileName = fileNameReader.readLine();
        fileNameReader.close();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        Integer maxID = 0; //8 символов.
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = fileReader.readLine();
            if (s == null) break;
            list.add(s);
            Integer id = Integer.parseInt(s.substring(0, 8).trim()); //8 символов.
            if (maxID < id) {
                maxID = id;
            }
        }
        maxID += 1;
        fileReader.close();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            stringBuilder.append(" ");
        }


        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        if (productName.length() < 30) {
            int spaces = 30 - productName.length();
            productName += stringBuilder.substring(0, spaces);
        }
        if (price.length() < 8) {
            int spaces = 8 - price.length();
            price += stringBuilder.substring(0, spaces);
        }
        if (quantity.length() < 4) {
            int spaces = 4 - quantity.length();
            quantity += stringBuilder.substring(0, spaces);
        }

        String outputLine = maxID + productName + price + quantity;
        for (String row : list) {
            fileWriter.write(row);
            fileWriter.newLine();
        }
        fileWriter.write(outputLine);
        fileWriter.close();
    }
}
