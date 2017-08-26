/*
 * Copyright (c) 2017. Код создан Д.Кляусом. Для использования кода в коммерческих продуктах - свяжитесь @ : deadlords@mail.ru
 */

package examplesCheck;

import java.io.*;
import static java.lang.System.out;


/**
 * Работа с интерфейсами Serializable и Externazible
 *
 * в классе всякие пробы работ с интерфейсами сведены в методы, которые работают с различными внутренними классами.
 * Например, startExternalizable - работа с Externalizable, который используется в классе SchemeExternalizable
 *
 * свод заметок вызывается методом help();
 *
 * todo: 1. в дальнейшем разгрести эту простыню и вынести в отдельные файлы все внутренние классы
 * todo: 2. разобраться с кастом классов при загрузке. Пока каст потомка в родителя не работает, получаем потомка
 * todo: 3. поработать с enum
 */

public class L001_Serialize {

    public static void main(String[] args) throws Exception{
        L001_Serialize srlz = new L001_Serialize();
        //srlz.startSerializationTypicalScheme();
        //srlz.startSerializationSingletonAndReadResolveMethod();
        //srlz.startSerializationWithClassChanged();
        //srlz.startSerializationNewClass(); //не пашет, потому что каст разных классов, сделать в отдельных классах реализацию обновлённого TestHuman
        //srlz.startSerializationUsingSpecialHandling();
        //srlz.startExternalizable();
        //srlz.startSerializationCacheProblem();
        help();
    }

    private void startSerializationCacheProblem() throws Exception{
        String fileName = "cache_pr.slz";
        CacheProblem cacheProblem = new CacheProblem();

        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        cacheProblem.setPriority(100);
        oos.writeObject(cacheProblem);

        cacheProblem.setPriority(200);
        oos.writeObject(cacheProblem);

        oos.flush();
        {
            oos.close();
            fos.close(); //если я закрываю oos, то автоматически закрывается и fos (?) почему?
        }
        out.println(cacheProblem.getPriority());

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        CacheProblem cacheProblemLoaded = (CacheProblem)ois.readObject();
        CacheProblem cacheProblem1 = (CacheProblem)ois.readObject();
        {
            ois.close();
            fis.close();
        }

        out.println(cacheProblemLoaded.getPriority());
        out.println(cacheProblem1.getPriority());


    }

    private void startExternalizable() throws Exception{
        out.println("======================================");
        out.println("Реализация Externalizable");
        out.println("======================================");
        out.println("");

        out.println("init...");
        SchemeExternalizable extern = new SchemeExternalizable();
        extern.setValue("save by Externalizable");

        out.printf("Value == [%s]%n",extern.getValue());
        out.println("saving...");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(baos);
        oo.writeObject(extern);

        {
            oo.close();
            baos.close(); //можно не делать, не имеет эффекта. https://docs.oracle.com/javase/7/docs/api/java/io/ByteArrayOutputStream.html
        }

        out.println("loading...");
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bais);

        out.println("init new...");
        SchemeExternalizable externLoaded = (SchemeExternalizable)oi.readObject();
        out.printf("Value == [%s]%n",extern.getValue());
        {
            oi.close();
            bais.close(); //можно не делать, не имеет эффекта. https://docs.oracle.com/javase/7/docs/api/java/io/ByteArrayInputStream.html
        }

    }

    private void startSerializationUsingSpecialHandling() throws Exception{
        out.println("======================================");
        out.println("Доп.настройки  сериализации, определение");
        out.println("методов readObject и writeObject в классе");
        out.println("======================================");
        out.println("");

        out.println("creating objects...");
        ChildDefault child1 = new ChildDefault(" :child without methods");
        ChildWithSerializationMethods child2 = new ChildWithSerializationMethods(" :child with methods");

        out.println("saving...");
        out.printf("Child value == [%s]%n", child1.value);
        out.printf("Child value == [%s]%n", child2.value);

        FileOutputStream fileOutputStream = new FileOutputStream("special.slz");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(child1);
        objectOutputStream.writeObject(child2);
        objectOutputStream.flush();
        {
            objectOutputStream.close();
            fileOutputStream.close();
        }

        FileInputStream fileInputStream = new FileInputStream("special.slz");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        out.println("creating new objects...");
        ChildDefault child1Loaded = (ChildDefault)objectInputStream.readObject();
        ChildWithSerializationMethods child2Loaded = (ChildWithSerializationMethods)objectInputStream.readObject();
        out.println("loading...");
        out.printf("Child value == [%s]%n", child1Loaded.value);
        out.printf("Child value == [%s]%n", child2Loaded.value);

        {
            objectInputStream.close();
            fileInputStream.close();
        }
    }

    private void startSerializationNewClass() throws Exception{
        out.println("======================================");
        out.println("Сериализация потомков/родителей, проба в потомка передать родителя");
        out.println("======================================");
        out.println("");

        String fileName = "method3.slz";

        TestHuman human = new TestHuman("Odin", 1000f);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(human);

        {
            objectOutputStream.close();
            fileOutputStream.close();
        }

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();

        TestHumanNew humanNew = (TestHumanNew)object;

        {
            objectInputStream.close();
            fileInputStream.close();
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
            objectOutputStream.close();
            fileOutputStream.close();
        }
        human.printAllGetMethodsAndStaticValues();

        FileInputStream fileInputStream = new FileInputStream("method2.slz");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();

        {
            objectInputStream.close();
            fileInputStream.close();
        }

        out.println(object.getClass().getCanonicalName());
        out.println();

        TestHuman humanC = (TestHuman)object; //в итоге всё равно класс TestHumanChanged

        humanC.printAllGetMethodsAndStaticValues();

        out.println(humanC.getClass().getCanonicalName());
        out.println();

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

        {
            outputStream.close();
            fileOutput.close();
        }

        FileInputStream fileInputStream = new FileInputStream("singleton.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();

        {
            objectInputStream.close();
            fileInputStream.close();
        }

        Singleton singletonRestored = (Singleton)object;

        singleton.printHashCodeAndInstanceRef();
        singletonRestored.printHashCodeAndInstanceRef();

        /*
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream1 = new ObjectInputStream(byteArrayInputStream);

        // Closing a ByteArrayOutputStream has no effect. The methods in this class can be called after the stream has been closed without generating an IOException.
        // https://docs.oracle.com/javase/7/docs/api/java/io/ByteArrayOutputStream.html

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

        private static final long SerialVersionUID = 1000L;
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
            System.out.printf("Сохранённый класс %s равен загруженному %s: ", this.getClass().getSimpleName(), o.getClass().getSimpleName());
            if(this == o) return  true;
            if(o==null||getClass()!=getClass()) return false;

            TestHuman human = (TestHuman)o;

            if(name!=null? !name.equals(human.name):name!=null) return false;
            return age!=0?age==human.age:age!=0;
        }
    }

    static class TestHumanChanged extends TestHuman {

        private static final long SerialVersionUID = 1001L;
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

        @Override
        public boolean equals(Object o) {
            TestHumanChanged thc = (TestHumanChanged)o;
            return super.equals(o)?sexMale==thc.sexMale:false;
        }
    }

    static class TestHumanNew implements Serializable {
        private static final long SerialVersionUID = 1000L;
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


    //реализация своих подходов при Сериализации
    //
    //При стандартной сериализации изменённое значение не сохраняется, поскольку
    //у потомков value наследуется из Parent (Parent не сериализуется).
    //В расширенной сериализации - сохраняем состояние value класса потомка и возвращаем обратно(?)

    //родительский класс с одной переменной, её значение изменяется потомками.
    static class Parent {
        protected String value = "\"parent string value\"";

        public Parent(){
            out.printf("Parent value == [%s]. Class: [%s]%n" , this.value ,this.getClass().getSimpleName());
        }
    }

     //стандартная сериализация класса
    static class ChildDefault extends Parent implements Serializable{
        public ChildDefault(String value){
            this.value +=value;
        }
    }

    //сериализация с дополнительной реализацией методов writeObject и readObject
    static class ChildWithSerializationMethods extends Parent implements Serializable{
        public ChildWithSerializationMethods(String value){
            this.value +=value;
        }

        private void writeObject(ObjectOutputStream oos) throws IOException{
            oos.defaultWriteObject();
            oos.writeObject(this.value);
        }
        private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException{
            ois.defaultReadObject();
            this.value = (String)ois.readObject();
        }
    }

    //Шаблон экстернализации
    static class SchemeExternalizable implements Externalizable{
        private String value = "";

        //конструктор без параметров обязателен при экстернализации
        //при десериализации он запускается первым, а потом загружаются сохранённые объекты
        public SchemeExternalizable(){
            out.println("..basic constructor..");
        }

        public void setValue(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
        public void writeExternal(ObjectOutput oo)
                throws IOException{
            oo.writeObject(this.value);
        }
        public void readExternal(ObjectInput oi)
                throws IOException, ClassNotFoundException{
            this.value = oi.readLine(); //метод readLine в ObjectInputStream явялется устаревшим, тут нет. Возможно в дальнейшем.
        }

    }

    static class CacheProblem implements Serializable{
        private int priority;
        public void setPriority(int priority){
            this.priority = priority;
        }
        public int getPriority(){
            return priority;
        }
    }

    static void help(){
        out.println("=============================================================");
        out.println("(De)Serializable / Externalizable learning");
        out.println();
        out.println("URL to Oracle Doc: ");
        out.println("Serializable:      \thttps://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html");
        out.println("Externalizable:    \thttps://docs.oracle.com/javase/7/docs/api/java/io/Externalizable.html");
            out.println("ObjectOutputStream:\thttps://docs.oracle.com/javase/8/docs/platform/serialization/spec/output.html");
        out.println();
        out.println("0. Необходимо подключить интерфейс Serializable");
        out.println("1. Cтат. переменные не сохраняются");
        out.println("2. Потоки и объекты которые их содержат вызовут ошибку NotSerializableException,");
        out.println("   нужно использовать модификатор transient");
        out.println("3. Для замены объекта (сохранение ссылки в памяти) при десериализации, в сериализуемый класс должен быть добавлен");
        out.println("   метод \"Object readResolve()\". Пока видел реализацию через Singleton, метод private; возвращается ссылка на Singleton ");
        out.println("4. Класс-потомок наследует интерфейс Serializable");
        out.println("5. Можно сохранить класс-потомок и загрузить как родительский класс");
        out.println("6. При десериализации потомка вызывается конструктор без параметров Род.класса (если он не сериализован), если такого конструкта нет - ошибка сериализации ");
        out.println("7. При экстернализации сначала вызывается конструктор без параметров сериализуемого класса, а потом подгружаются данные. ");
        out.println("8. Компилятор формирует значение serialVersionUID в зависимости от структуры сериализуемого объекта, есть вероятность изменения значения в зависимости от версии компилятора.\n" +
                    "   Рекомендуется сериализованному классу добавлять private static final long serialVersionUID. В SDK есть утилита serialver, которая поможет сформировать реальный svUID.\n" +
                    "   http://skipy.ru/technics/serialization.html");
        out.println("9. Если необходимы особые подходы при обычной сериализации, то должны быть реализованы методы: \n" +
                    "   - private void readObject() throws IOException, ClassNotFoundException, \n" +
                    "   - private void writeObject() throws IOException, \n" +
                    "   - private void readObjectNoData() throws ObjectStreamException\n" +
                    "   с другими модификаторами доступа (default, protected, public) методы не работают (не выбрасывают ошибку, просто не вызываются).");
        out.println("10. Интерфейс Externalizable - потомок Serializable. Для экстернализации в классе реализуются public методы writeExternal(ObjectOutput o) и readExternal(ObjectInput i)");
        out.println("11. Если класс реализует два интерфейса Externalizable и Serializable, то приоритетным является Externalizable");
        out.println("12. Вариант запрета сериализации - реализовать методы readObject и writeObject c выбросом ошибки NotSerializableException");
    }
}
