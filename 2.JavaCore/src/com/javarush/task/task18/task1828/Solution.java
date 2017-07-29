package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        boolean isDelete = false;
        String productName = ""; //название товара, 30 chars (60 bytes).
        String price = ""; //цена, 8 символов.
        String quantity = ""; //количество, 4 символа.
        String fileName = "";
        String id = "";
        String outputLine = "";
        if (args.length > 0 && (args[0].equals("-d") || args[0].equals("-u"))) {
            if (args[0].equals("-d")) isDelete = true;

            id = args[1];

            if (!isDelete) {
                productName = args[2];
                price = args[3];
                quantity = args[4];
                outputLine = prooductLineBuilder(id, productName, price, quantity);
            }

            BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
            fileName = fileNameReader.readLine();
            fileNameReader.close();

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            ArrayList<String> list = new ArrayList<>();
            while (true) {
                String s = fileReader.readLine();
                if (s == null) break;
                String value = s.substring(0, 8).trim();
                if (value.equals(id)) {
                    if (!isDelete) list.add(outputLine);
                } else {
                    list.add(s);
                }
            }
            fileReader.close();

            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            for (int i = 0; i < list.size() - 1; i++) {
                fileWriter.write(list.get(i));
                fileWriter.newLine();
            }
            fileWriter.write(list.get(list.size() - 1));
            fileWriter.close();
        }
    }

    public static String prooductLineBuilder(String id, String productName, String price, String quantity) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            stringBuilder.append(" ");
        }

        if (id.length() < 8) {
            int spaces = 8 - id.length();
            id += stringBuilder.substring(0, spaces);
        }
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
        return id + productName + price + quantity;
    }
}
