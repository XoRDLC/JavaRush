package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream def = System.out;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new OwnStream(new PrintStream(out));
        System.setOut(printStream);


        testString.printSomething();

        System.setOut(def);
        System.out.println(out.toString());

    }

    public static class OwnStream extends PrintStream {
        private int counter = 0;
        private String ADVERT = "JavaRush - курсы Java онлайн";

        private PrintStream stream;

        public OwnStream(PrintStream stream) {
            super(stream);
            this.stream = stream;
        }

        public void println(String string) {
            stream.println(string);
            if (counter == 1) {
                stream.println(ADVERT);
                counter = 0;
            } else {
                counter++;
            }
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
