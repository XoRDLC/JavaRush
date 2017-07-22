/*
 * Copyright (c) 2017. Код создан Д.Кляусом. Для использования кода в коммерческих продуктах - свяжитесь @ : deadlords@mail.ru
 */

package examplesCheck;

import java.io.*;
import static java.lang.System.out;

public class L001_Serialize {

    public static void main(String[] args) throws Exception{
        help();

        TestHuman human  =  new TestHuman("Vasya", 10f);
        human.printAllGetMethodsAndStaticValues(); //вывод всех значений объекта

        //save
        FileOutputStream fileOutput = new FileOutputStream("test.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(human);
        fileOutput.close();
        outputStream.close();

        TestHuman.check = "changed";

        //restore
        FileInputStream fileInputStream = new FileInputStream("test.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();

        TestHuman human1 = (TestHuman)object;
        human1.printAllGetMethodsAndStaticValues();
        out.println(human.equals(human1));

        Singleton singleton = Singleton.getInstance();

        fileOutput = new FileOutputStream("singleton.dat");
        outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(singleton);

        { fileOutput.close(); outputStream.close(); }

        fileInputStream = new FileInputStream("singleton.dat");
        objectInputStream = new ObjectInputStream(fileInputStream);
        object = objectInputStream.readObject();

        { fileInputStream.close(); objectInputStream.close(); }

        Singleton singletonRestored = (Singleton)object;

        singleton.getStatus();
        singletonRestored.getStatus();

        /*
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(singleton);

        { objectOutputStream.close(); }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream1 = new ObjectInputStream(byteArrayInputStream);
        Object objectSingleton = objectInputStream1.readObject();

        { objectInputStream1.close(); byteArrayInputStream.close(); byteArrayOutputStream.close(); }

        Singleton singletonRestored = (Singleton)objectSingleton;

        //The singleton behavior has been broken
        System.out.println("Instance reference check : " + singleton.getInstance());
        System.out.println("Instance reference check : " + singletonRestored.getInstance());
        System.out.println("=========================================================");
        System.out.println("Object reference check : " + singleton);
        System.out.println("Object reference check : " + singletonRestored);
*/
    }

    static class TestHuman implements Serializable {
        private String name;
        private float age;
        private static String check = "original";
        transient private PrintStream stream = System.out; //not serializableS

        private TestHuman(){}
        public TestHuman(String name){ this.name = name;this.age = 0.1f; }
        public TestHuman(String name, float age){ this.name = name;this.age = age; }

        public String getName(){ return this.name; }
        public float getAge(){
            return this.age;
        }
        public void setName(String name){}
        public void setAge(float name){}
        public void printAllGetMethodsAndStaticValues(){
            out.println(hashCode());
            out.println(getName());
            out.println(getAge());
            out.println(check);
        }


        public boolean equals(Object o){
            if(this == o) return  true;
            if(o==null||getClass()!=getClass()) return false;

            TestHuman human = (TestHuman)o;

            if(name!=null? !name.equals(human.name):name!=null) return false;
            return age!=0?age==human.age:age!=0;
        }
    }

    static class Singleton implements Serializable{
        private static Singleton instanceSingleton;

        public static Singleton getInstance(){
            if(instanceSingleton == null){
                instanceSingleton = new Singleton();
            }
            return instanceSingleton;
        }

        private Object readResolve() throws ObjectStreamException{
            return instanceSingleton;
        }

        private Singleton(){}

        public void getStatus(){
            out.println(this.hashCode());
            out.println(getInstance());
        }
    }

    static void help(){
        out.println("(De)Serializable / Externalizable learning");
        out.println();
        out.println("URL to Oracle Doc: ");
        out.println("Serializable:\thttps://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html");
        out.println();
        out.println("0. Необходимо подключить интерфейс Serializable");
        out.println("1. Cтат. переменные не сохраняются");
        out.println("2. Потоки и объекты которые их содержат вызовут ошибку NotSerializableException,");
        out.println("   нужно использовать модификатор transient");
        out.println("3. Для замены объекта (сохранение ссылки в памяти) при десериализации, в восстаналиваемый объекте должен быть добавлен");
        out.println("   метод \"Object readResolve()\". Пока видел реализацию через Singleton, метод private; возвращается ссылка на Singleton ");
        out.println();
    }
}
