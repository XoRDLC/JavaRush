package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String url = bufferedReader.readLine();
        String params[] = (url.split("\\?")[1]).split("\\&");
        String obj = "";
        String param = "";
        for (int i = 0; i < params.length; i++) {
            param = params[i].split("=")[0];
            if (param.equals("obj")) {
                obj = params[i].split("=")[1];
            }
            System.out.print(param + " ");
        }
        System.out.println();
        if (!obj.equals("")) {
            try {
                alert(Double.parseDouble(obj));
            } catch (NumberFormatException e) {
                alert(obj);
            }
        }


        bufferedReader.close();
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
