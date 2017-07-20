/*
 * Copyright (c) 2017. Код создан Д.Кляусом. Для использования кода в коммерческих продуктах - свяжитесь @ : deadlords@mail.ru
 */

package examplesCheck;

import java.io.*;
import static java.lang.System.out;

public class L001_Serialize {


    public static void main(String[] args) throws Exception{

        TestHuman human  =  new TestHuman("Vasya", 10f);


        FileOutputStream fileOutput = new FileOutputStream("test.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(human);
        fileOutput.close();
        outputStream.close();

        FileInputStream fileInputStream = new FileInputStream("test.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();

        out.println(human.hashCode());
        out.println(human.getName());
        out.println(human.getAge());
        out.println(TestHuman.check);
        TestHuman.check = "changed";


        TestHuman human1 = (TestHuman)object;
        out.println(human1.hashCode());
        out.println(human1.getName());
        out.println(human1.getAge());
        out.println(TestHuman.check);

        out.println(human.equals(human1));

    }


    static class TestHuman implements Serializable {
        private String name;
        private float age;
        private static String check = "original";

        private TestHuman(){
            super();
        }


        public TestHuman(String name){
            this.name = name;
            this.age = 0.1f;        }

        public TestHuman(String name, float age){
            this.name = name;
            this.age = age;
        }

        public String getName(){
            return this.name;
        }

        public float getAge(){
            return this.age;
        }

        public void setName(String name){}
        public void setAge(float name){}

        public boolean equals(Object o){
            if(this == o) return  true;
            if(o==null||getClass()!=getClass()) return false;

            TestHuman human = (TestHuman)o;

            if(name!=null? !name.equals(human.name):name!=null) return false;
            return age!=0?age==human.age:age!=0;
        }
    }
}
