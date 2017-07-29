package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> fileList = new ArrayList<>();
        String fileName = "";
        while (true) {
            fileName = fileNameReader.readLine();
            if (fileName == null) break;
            if (fileName.equals("end")) break;
            fileList.add(fileName);

        }
        fileNameReader.close();
        Collections.sort(fileList);
        String valueCheck = fileList.get(0);
        Integer index = 0;

        for (int i = valueCheck.length() - 1; i >= 0; i--) {
            if (valueCheck.charAt(i) == '.') {
                index = i;
                break;
            }
        }
        String outputFile = valueCheck.substring(0, index);

        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        for (int i = 0; i < fileList.size(); i++) {
            FileInputStream fileInputStream = new FileInputStream(fileList.get(i));
            if (fileInputStream.available() > 0) {
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer);
                fileOutputStream.write(buffer);
            }
            fileInputStream.close();
        }
        fileOutputStream.close();


    }
}
