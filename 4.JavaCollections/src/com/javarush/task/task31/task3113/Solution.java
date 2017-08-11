package com.javarush.task.task31.task3113;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    private static long dirCount;
    private static long fileCount;
    private static long sizeCount;


    public static void main(String[] args) throws IOException {
        //System.setOut(new PrintStream(new FileOutputStream("check1.txt")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathString = reader.readLine();
        Path path = Paths.get(pathString);
        if(Files.isDirectory(path)){
            Files.walkFileTree(path, new GetTreeStatistics());
            System.out.printf("Всего папок - %s%n", dirCount-1);
            System.out.printf("Всего файлов - %s%n", fileCount);
            System.out.printf("Общий размер - %s%n", sizeCount);
        }
        else{
            System.out.printf("%s - не папка%n", path);
        }
    }

    public static class GetTreeStatistics extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            //System.out.printf("PRE:\t %s\t-\t%s%n",dir, Files.size(dir));
            dirCount++;
            sizeCount+=Files.size(dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            //System.out.printf("FILE:\t %s\t-\t%s%n",file, Files.size(file));
            fileCount++;
            sizeCount+=Files.size(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

            //System.out.printf("SKIP:\t %s\t-\t%s%n",file, Files.size(file));
            if(Files.isDirectory(file)){dirCount++;}
            else{fileCount++;}

            sizeCount+=Files.size(file);
            return FileVisitResult.SKIP_SUBTREE;
        }
    }
}
