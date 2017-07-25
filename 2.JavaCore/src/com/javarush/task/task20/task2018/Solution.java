package com.javarush.task.task20.task2018;

import java.io.*;
import java.util.ArrayList;

/* 
Найти ошибки
*/
public class Solution implements Serializable {
    public static class A {
        protected String name = "A";

        //родительский класс, если не сереализируется, должен содержать конструктор без параметров, поскольку
        //при десереализации он будет выполнен. В потомке, если он сереализируется, пустой конструктор не нужен
        //(если экстернализируется, то нужен - будет запущен конструктор, а потом десереализуются объекты/поля).

        public A(){
        }

        public A(String name) {
             this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public B(String name) {
            super(name);
            this.name += name;

        }

        /*
        методы должны быть private, если др.модификатор, то выхваны методы не будут
        ?связано с приоритетами модификаторов? разобраться
        UPD1:
            Classes that require special handling during the serialization and deserialization process
            must implement special methods with these EXACT signatures:

            private void writeObject(java.io.ObjectOutputStream out)
                    throws IOException
            private void readObject(java.io.ObjectInputStream in)
                    throws IOException, ClassNotFoundException;
            private void readObjectNoData()
                    throws ObjectStreamException;

            https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html

        Cвязано с  конкретной реализацией в Java, понять почему (конкретно прописано чтобы private был или
        это что-то из основ в Java). Посмотреть код ObjectOutputStream.
      */
        private void writeObject(ObjectOutputStream oos) throws IOException{
            //System.out.println("B, method write");
            oos.defaultWriteObject();
            oos.writeObject(this.name);
        }
        private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException{
            //System.out.println("B, method read");
            ois.defaultReadObject();
            this.name = (String)ois.readObject();
        }
    }

     public static void main(String[] args) throws IOException, ClassNotFoundException {
        //ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        FileOutputStream arrayOutputStream = new FileOutputStream("checkThis");
        ObjectOutputStream oos = new ObjectOutputStream(arrayOutputStream);

        Solution solution = new Solution();
        B b = solution.new B("B2");

        System.out.println(b.name);

        //b.writeObject(oos);

        /* Выбрасывает NotActiveException, как указано в классе ObjectOutputStream.java
            This may only be called from the writeObject method of the
            class being serialized. It will throw the NotActiveException if it is
            called otherwise.
        */

        oos.writeObject(b);

        //ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
        FileInputStream arrayInputStream = new FileInputStream("checkThis");
        ObjectInputStream ois = new ObjectInputStream(arrayInputStream);

        B b1 = (B)ois.readObject();
        System.out.println(b1.name);

        //задача не выполнена, возвращает "A", поскольку сохраняется только name super-класса
    }
}
