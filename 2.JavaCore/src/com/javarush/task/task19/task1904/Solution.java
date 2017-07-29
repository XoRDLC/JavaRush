package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String string[] = fileScanner.nextLine().split(" ");
            return new Person(string[1], string[2], string[0], new Date(string[3] + "." + string[4] + "." + string[5]));
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}
