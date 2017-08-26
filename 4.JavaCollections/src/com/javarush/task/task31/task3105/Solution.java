package com.javarush.task.task31.task3105;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/


//нерабочий вариант
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipName = args[1];

        String zipFileName = "new\\" + Paths.get(args[0]).getFileName().toString();

        FileInputStream fileInputStream = new FileInputStream(zipName);
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);

        ZipEntry zipEntry;

        Map<ZipEntry, byte[]> map = new HashMap<>();
        while((zipEntry=zipInputStream.getNextEntry())!=null){
            System.out.printf("NAME: %s; AVAIL: %s%n", zipEntry.getName(), zipInputStream.available());

            int b;
            while((b = zipInputStream.read())!=-1){
                System.out.println(b);
            }
            zipInputStream.closeEntry();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(zipName);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        zipOutputStream.putNextEntry(new ZipEntry(zipFileName));

        File file = new File(fileName);
        Files.copy(file.toPath(), zipOutputStream);

        for(Map.Entry<ZipEntry, byte[]> entry : map.entrySet()){
            System.out.println(entry.getKey().getName());
            System.out.println(entry.getKey().getSize());
            zipOutputStream.putNextEntry(entry.getKey());
            zipOutputStream.write(entry.getValue());
            zipOutputStream.flush();
        }

        zipInputStream.close();
        zipOutputStream.close();
        fileInputStream.close();








    }
}
