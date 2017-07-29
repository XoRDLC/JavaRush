package com.javarush.task.task19.task1902;

/* 
Адаптер
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {

    private FileOutputStream fileOutputStream;

    public AdapterFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    public static void main(String[] args) {

    }

    @Override
    public void flush() throws IOException {
        fileOutputStream.flush();
    }

    @Override
    public void writeString(String s) throws IOException {

        byte[] buffer = new byte[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) chars[i];
        }

        fileOutputStream.write(buffer);
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.close();
    }


}

