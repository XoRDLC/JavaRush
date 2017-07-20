package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        ReadThread readThread;
        while (true) {
            String fileName = fileNameReader.readLine();
            if (fileName.equals("exit")) break;
            readThread = new ReadThread(fileName);
            readThread.start();

        }
        fileNameReader.close();
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;

        }

        public void run() {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                HashMap<Byte, Integer> hashMap = new HashMap<>();
                int max = 0;
                int b = 0;

                while (fileInputStream.available() > 0) {
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    for (int i = 0; i < buffer.length; i++) {
                        Byte value = buffer[i];
                        if (hashMap.containsKey(value)) {
                            hashMap.replace(value, hashMap.get(value) + 1);
                            if (hashMap.get(value) > max) {
                                max = hashMap.get(value);
                                b = value;
                            }
                        } else {
                            hashMap.put(value, 1);
                        }
                    }
                }
                synchronized (resultMap) {
                    resultMap.put(fileName, b);
                }
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
