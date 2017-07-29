package com.javarush.task.task20.task2023;

/* 
Делаем правильный вывод
*/

/**
 * Cхема такова:
 * При public методах вызов идёт снизу вверх, от потомка к родителю.
 * Т.е., на этапе, когда в классе А вызывается method1, если у А.method1 модификатор public, сначала проверяется
 *  наличие этого метода в классе С (потомок, созданный в main, который наследует методы А и В и переопределяет их),
 * если в С нет method1, проверяется В, а потом А. (понятно же)
 *
 * Если А.method1 имеет модификатор private, то выполнятся будет он.
 * Вроде понимаю почему так - метод недоступен другим классам (Методы с private не наследуются, но от прямого потомка через
 * super.method1 можно обратиться), значит, если иерархия вызовов методов из С привела к вызову method1 из класса А, то
 * приоритет имеет метод с модификатором private. Т.е., сначала выполняются внутренние (свойственные только А) методы,
 * а потом все остальные, от объекта-потомка к родителям  (?).
 */

public class Solution {
    public static void main(String[] s) {
        A a = new C();
        a.method2();
    }

    public static class A {

        //результат компиляции показывает интересное, если в B.method1 активен вызов super.method1(), а в А - конструктор.
        //результат компиляции:
        //
        //  A: [access$000]; [4104]
        //  A: [method1]; [2]
        //  A: [method2]; [1]
        //  C class, method2
        //  A class, method2
        //  A class, method1
        //  A class, method1
        //  B class, method1
        //
        //  ВЫходит что access$000 - обращение к private методу. И, похоже, компилятор обрабатывает сначала запросы к приватным
        //  методам, и создаёт для super.method1() (из В) либо какой-то промежуточный класс А с одним методом, либо добавляет
        //  реализацию приватного метода в родительский класс для В, либо формирует готовый ответ для вызывающего метода по ссылке  access$000,
        //  либо что-то ещё и я просто не понимаю как это работает. А потом идут все методы класса А.
        //  UPD: Это синтетический метод (?), я ещё не знаю что это.
/*
        private int counter;
        public A(){
            for(java.lang.reflect.Method a: A.class.getDeclaredMethods()){
                counter++;
                System.out.printf("A: [%s]; [%s], [%s], [%b]%n",a.getName(), a.getModifiers(), a.hashCode(), a.isSynthetic());
            }
            System.out.println(counter);
        }
        //проверка, что методы выводятся не в алфавитном порядке. Многократный запуск показал, что перечень выводится в любом порядке,
        //но access$000 всегда первый. Т.е. это связанно с тем, что метод синтетический. Как это реализуется - нужно разбираться .
        private void aaa(){}
        private void zzz(){}
*/
        private void method1() {
            System.out.println("A class, method1");
        }

        public void method2() {
            System.out.println("A class, method2");
            method1();
        }
    }

    public static class B extends A {

        public void method1() {
            super.method2();
            //super.method1();
            System.out.println("B class, method1");
        }
        public void method2() {
            System.out.println("B class, method2");
        }
    }

    public static class C extends B {
        public void method1() {
            System.out.println("C class, method1");
        }

        public void method2() {
            System.out.println("C class, method2");
            super.method1();
        }
    }
}
