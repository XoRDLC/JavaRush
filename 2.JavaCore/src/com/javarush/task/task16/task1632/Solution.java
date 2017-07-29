package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new EndlessThread());
        threads.add(new InterruptedThread());
        threads.add(new UraThread());
        threads.add(new MessageThread());
        threads.add(new NumbersThread());
    }

    public static void main(String[] args) {
    }

    public static class EndlessThread extends Thread {
        public void run() {
            while (true) {
            }
        }
    }

    public static class InterruptedThread extends Thread {
        public void run() {
            try {
                while (true) {
                    if (Thread.interrupted()) throw new InterruptedException();
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class UraThread extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MessageThread extends Thread implements Message {
        private boolean isCancel = false;

        public void run() {
            while (!isCancel) {
            }
        }

        @Override
        public void showWarning() {
            isCancel = true;
        }
    }

    public static class NumbersThread extends Thread {
        private BufferedReader bufferedReader;

        public void run() {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Integer sum = 0;
            try {
                while (true) {
                    String s = bufferedReader.readLine();
                    if (s.equals("N")) {
                        break;
                    }
                    sum += Integer.parseInt(s);
                }
                System.out.println(sum);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    }
}