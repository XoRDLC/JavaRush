package com.javarush.task.task16.task1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException, IOException {
        Read3Strings t1 = new Read3Strings();
        Read3Strings t2 = new Read3Strings();

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        //add your code here - добавьте код тут

        t1.printResult();
        t2.printResult();

        reader.close();
    }

    public static class Read3Strings extends Thread {
        private String s = "";

        public void run() {

            try {
                s = reader.readLine();
                for (int i = 0; i < 2; i++) {
                    s += " " + reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void printResult() {
            System.out.println(s);
        }
    }
    //add your code here - добавьте код тут
}
