package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.HashMap;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        if(args.length==0){
            args = new String[2];
            args[0] = "..";
            args[1] = "";

        }
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File file = new File(path);
        FileWriter fileWriter = new FileWriter("fileCheck.txt");


        System.out.println("file.getName()\t" + file.getName());
        System.out.println("file.getCanonicalPath()\t" + file.getCanonicalPath());
        System.out.println("file.getParentFile()\t" + file.getParentFile());
        System.out.println("file.getParent()\t" + file.getParent());
        System.out.println("file.getAbsolutePath()\t" + file.getAbsolutePath());
        System.out.println("file.list()\t" + file.list());
        System.out.println("file.canExecute()\t" + file.canExecute());
        System.out.println("file.canRead()\t" + file.canRead());
        System.out.println("file.canWrite()\t" + file.canWrite());
        System.out.println("file.exists()\t" + file.exists());
        System.out.println("file.getFreeSpace()\t" + file.getFreeSpace());
        System.out.println("file.getPath()\t" + file.getPath());
        System.out.println("file.getTotalSpace()\t" + file.getTotalSpace());
        System.out.println("file.getUsableSpace()\t" + file.getUsableSpace());
        System.out.println("file.isAbsolute()\t" + file.isAbsolute());
        System.out.println("file.isDirectory()\t" + file.isDirectory());
        System.out.println("file.isFile()\t" + file.isFile());
        System.out.println("file.isHidden()\t" + file.isHidden());
        System.out.println("file.lastModified(\t" + file.lastModified());
        System.out.println("file.length()\t" + file.length());
        System.out.println("file.toPath()\t" + file.toPath());
        System.out.println("file.toString()\t" + file.toString());
        System.out.println("file.toURI()\t" + file.toURI());

    }

    public static HashMap<String, File> directoryOpenner(File directory){
        HashMap<String, File> fileList = new HashMap<>();
        File file = new File(directory.getAbsolutePath());

        return fileList;
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
