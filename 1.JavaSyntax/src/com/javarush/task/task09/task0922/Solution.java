package com.javarush.task.task09.task0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Какое сегодня число?
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Date dDate = new Date();

        SimpleDateFormat simpDate = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        dDate = simpDate.parse(bufferedReader.readLine());
        //bufferedReader.readLine())
        simpDate.applyLocalizedPattern("MMM dd, yyyy");


        System.out.println(simpDate.format(dDate).toUpperCase());


    }
}
