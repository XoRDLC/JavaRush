package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/


public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zip = args[1];
        String newFileName = "new" + File.separator + Paths.get(fileName).getFileName().toString();

        Map<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();
        try (ZipInputStream zipReader = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipReader.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipReader.read(buffer)) != -1)
                    byteArrayOutputStream.write(buffer, 0, count);
                map.put(zipEntry, byteArrayOutputStream);
            }
        }

        FileOutputStream fos = new FileOutputStream(zip);
        ZipOutputStream zos = new ZipOutputStream(fos);

        for(Map.Entry<ZipEntry, ByteArrayOutputStream> entry: map.entrySet()){
            if(!Paths.get(entry.getKey().getName()).equals(Paths.get(newFileName))){
                zos.putNextEntry(new ZipEntry(entry.getKey().getName()));
                zos.write(entry.getValue().toByteArray());
            }
        }

        zos.putNextEntry(new ZipEntry(newFileName));
        File file = new File(fileName);
        Files.copy(file.toPath(), zos);

        zos.close();
        fos.close();
    }
}
