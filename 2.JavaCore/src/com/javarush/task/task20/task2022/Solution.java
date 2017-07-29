package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException, IOException {
        this.stream = new FileOutputStream(fileName );
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception{

        String solFileName = "solution.txt";
        String serializeFileName = "task2022.slz";

        Solution sol = new Solution(solFileName);
        sol.writeObject("Before save");

        FileOutputStream fos = new FileOutputStream(serializeFileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sol);
        {
            oos.flush();
            oos.close();
            sol.close();
        }

        FileInputStream fis = new FileInputStream(serializeFileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Solution solNew = (Solution)ois.readObject();
        solNew.writeObject("After load");
        {
            ois.close();
            fis.close();
            solNew.close();
        }

        BufferedReader reader = new BufferedReader(new FileReader(solFileName));
        String lineString = "";
        while((lineString=reader.readLine())!=null){
            System.out.println(lineString);
        }

    }
}
