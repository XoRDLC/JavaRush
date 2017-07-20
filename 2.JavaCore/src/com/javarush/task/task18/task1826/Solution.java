package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        String status = args[0];
        FileInputStream fileInputStream = new FileInputStream(args[1]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[2]);
        int encryptDecryptValue = 35;

        if (status.equals("-e")) encryptDecryptValue = encryptDecryptValue;
        if (status.equals("-d")) encryptDecryptValue = -encryptDecryptValue;

        while (fileInputStream.available() > 0) {
            fileOutputStream.write(fileInputStream.read() + encryptDecryptValue);
        }

        fileInputStream.close();
        fileOutputStream.close();

        /*
        if(args[0].equals("-e")) {encrypt(args[1], args[2]);}
        if(args[0].equals("-d")) {decrypt(args[1], args[2]);}
    }
    public static void encrypt(String inputFile, String outputFile) throws IOException, FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        if(fileInputStream.available()>0){
            int b = fileInputStream.read();
            fileOutputStream.write(b-35);
        }
        fileInputStream.close();
        fileOutputStream.close();

    }
    public static void decrypt(String inputFile, String outputFile) throws IOException, FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        if(fileInputStream.available()>0){
            int b = fileInputStream.read();
            fileOutputStream.write(b+35);
        }
        fileInputStream.close();
        fileOutputStream.close();*/
    }
}
