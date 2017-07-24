/*
 * Copyright (c) 2017. Код создан Д.Кляусом. Для использования кода в коммерческих продуктах - свяжитесь @ : deadlords@mail.ru
 */

package examplesCheck;

import java.io.*;
import static java.lang.System.out;

public class L001_Serialize {

    public static void main(String[] args) throws Exception{
        //(new L001_Serialize()).startSerializationTypicalScheme();
        //(new L001_Serialize()).startSerializationSingletonAndReadResolveMethod();
        (new L001_Serialize()).startSerializationWithClassChanged();
        //(new L001_Serialize()).startSerializationNewClass(); //не пашет, потому что каст разных классов, сделать в отдельных классах реализацию обновлённого TestHuman
        //help();
    }

    private void startSerializationNewClass() throws Exception{
        String fileName = "method3.slz";

        TestHuman human = new TestHuman("Odin", 1000f);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(human);

        {
            fileOutputStream.close();
            objectOutputStream.close();
        }

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();

        TestHumanNew humanNew = (TestHumanNew)object;

        {
            fileInputStream.close();
            objectInputStream.close();
        }

        out.println(human.equals(humanNew));
        human.printAllGetMethodsAndStaticValues();
        humanNew.printAllGetMethodsAndStaticValues();

    }

    private void startSerializationWithClassChanged() throws Exception {
        out.println("======================================");
        out.println("Сериализация потомков/родителей");
        out.println("======================================");
        out.println("");

        TestHuman human = new TestHumanChanged("Vasilisa", 10f, false);

        FileOutputStream fileOutputStream = new FileOutputStream("method2.slz");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(human);

        {
            fileOutputStream.close();
            objectOutputStream.close();
        }
        human.printAllGetMethodsAndStaticValues();

        FileInputStream fileInputStream = new FileInputStream("method2.slz");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();

        {
            fileInputStream.close();
            objectInputStream.close();
        }

        TestHuman humanC = (TestHuman)object; //в итоге всё равно класс TestHumanChanged
        humanC.printAllGetMethodsAndStaticValues();
        out.println(humanC.getClass().getCanonicalName());
        out.println(human.equals(humanC));
    }

    private void startSerializationTypicalScheme() throws Exception {
        out.println("======================================");
        out.println("Шаблон использования сериализации");
        out.println("======================================");
        out.println("");

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
    }

    private void startSerializationSingletonAndReadResolveMethod() throws Exception {
        out.println("======================================");
        out.println("Шаблон использования синглтона,\n" +
                    "с перезаписью объекта (сохранением\n" +
                    "ссылки в памяти).");
        out.println("======================================");
        out.println("");

        Singleton singleton = Singleton.getInstance();

        FileOutputStream fileOutput = new FileOutputStream("singleton.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(singleton);

        { fileOutput.close(); outputStream.close(); }

        FileInputStream fileInputStream = new FileInputStream("singleton.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();

        { fileInputStream.close(); objectInputStream.close(); }

        Singleton singletonRestored = (Singleton)object;

        singleton.printHashCodeAndInstanceRef();
        singletonRestored.printHashCodeAndInstanceRef();

        /*
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream1 = new ObjectInputStream(byteArrayInputStream);
        */
    }

    static class Singleton implements Serializable{
        private static Singleton singletonInstance;

        public static Singleton getInstance() {
            if(singletonInstance==null) singletonInstance = new Singleton();
            return singletonInstance;
        }
        private Singleton(){}

        public Object readResolve() throws ObjectStreamException{return singletonInstance;}

        public void printHashCodeAndInstanceRef(){
            out.println(hashCode());
            out.println(this);
        }
    }



    static class TestHuman implements Serializable {

        private static final long SerialVersionUID = 1000;
        private String name  = "";
        private float age = 0.1f;
        private static String check = "original";
        transient private PrintStream stream = System.out; //not serializableS

        private TestHuman(){}
        public TestHuman(String name){ this.name = name;}
        public TestHuman(String name, float age){ this.name = name;this.age = age; }

        public String getName(){ return this.name; }
        public float getAge(){
            return this.age;
        }
        public void setName(String name){this.name=name;}
        public void setAge(float age){this.age = age;}

        public void printAllGetMethodsAndStaticValues(){
            out.println("Hash: \t" + hashCode());
            out.println("Name: \t" + getName());
            out.println("Age: \t" + getAge());
            out.println("Static:\t" + check);
        }


        public boolean equals(Object o){
            if(this == o) return  true;
            if(o==null||getClass()!=getClass()) return false;

            TestHuman human = (TestHuman)o;

            if(name!=null? !name.equals(human.name):name!=null) return false;
            return age!=0?age==human.age:age!=0;
        }
    }

    static class TestHumanChanged extends TestHuman {
        private boolean sexMale = true;

        TestHumanChanged(String name){super(name);}
        TestHumanChanged(String name, float age){super(name, age);}
        TestHumanChanged(String name, float age, boolean sexMale){super(name, age); this.sexMale = sexMale;}

        public boolean getSexMale(){return sexMale;}

        public void setSexMale(boolean sexMale){this.sexMale = sexMale;}

        @Override
        public void printAllGetMethodsAndStaticValues() {
            super.printAllGetMethodsAndStaticValues();
            out.println("Male: \t" + sexMale);

        }
    }

    static class TestHumanNew implements Serializable {
        private static final long SerialVersionUID = 1000;
        private String name  = "";
        private float age = 0.1f;
        private static String check = "original";
        transient private PrintStream stream = System.out; //not serializableS
        private boolean sexMale = true;

        private TestHumanNew(){}
        public TestHumanNew(String name){ this.name = name;}
        public TestHumanNew(String name, float age){ this.name = name;this.age = age; }
        public TestHumanNew(String name, float age, boolean sexMale){this.name = name;this.age = age; this.sexMale = sexMale;}

        public String getName(){ return this.name; }
        public float getAge(){
            return this.age;
        }
        public boolean getSexMale(){return sexMale;}
        public void setName(String name){this.name=name;}
        public void setAge(float age){this.age = age;}
        public void setSexMale(boolean sexMale){this.sexMale = sexMale;}

        public void printAllGetMethodsAndStaticValues(){
            out.println("Hash: \t" + hashCode());
            out.println("Name: \t" + getName());
            out.println("Age: \t" + getAge());
            out.println("Static:\t" + check);
            out.println("Male: \t" + sexMale);
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
        out.println("4. Класс-потомок наследует интерфейс Serializable");
        out.println("5. Можно сохранить класс-потомок и загрузить как родительский класс");
    }
}
