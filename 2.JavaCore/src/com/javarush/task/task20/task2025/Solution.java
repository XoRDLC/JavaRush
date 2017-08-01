package com.javarush.task.task20.task2025;


/*
Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M (количество цифр) = 3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания.

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/

import java.io.*;
import java.util.*;

//валиадатор пройден.
//подсмотрел решение: http://rextester.com/KHEI65875
//за что автору большое спасибо.
//
//мои варинаты тормозные, от ~50 сек для Int, для long - неразумно долго.
//Решал через перебор чисел от 1 до N с выделением цифр, цифры в числе только в возрастающем порядке
//
//В этом варианте формируется массив чисел до N-размерности (если N=370, то считаются числа от 001 до 999),
// отсекаются повторы (типа 011, 101, 110) через операции с массивом:
// низшему разряду присваивается сл.значение старшего. т.е. сначала обрабатываются значения
// 001-009, затем 011-019, 022-029 и т.д.
//Изменения:
// убрал расчёт степени каждого числа и добавил матрицу степеней.
// некоторые расчёты загнал в переменные (Math.log10() и array[i].length)

//c матрицей быстрее в ~2 раза. 21-23 сек. vs 36-40 для Long
// с переменными, где-то 1-2 сек сокращает. Запускал цикл в 250 повторов для N = Integer.MAX_VALUE. Нужны тесты для Long

public class Solution {
    public static long[] getNumbers(long N){
        long result[] = null;
        //найденные числа помещаем в коллекцию
        Set<Long> treeSet = new TreeSet<>();

        //убираем расчёт в переменную
        int mathLog10N = (int)Math.log10(N);

        //матрица степеней.
        long powsMatrix[][] = new long[10][mathLog10N+1];

        for(int i=0;i<10; i++){ //4 байта
            for(int j=0; j<=mathLog10N; j++){  //4 байта
                if(j>0){
                    powsMatrix[i][j] =powsMatrix[i][j-1]*i;
                }
                else {
                    powsMatrix[i][j] =i;
                }
            }
        }

        //матрица чисел, первая размерность - количество цифр в N
        int array[][] = new int[mathLog10N+1][];

        //треугольная матрица, размер каждой i-й матрицы - степень
        for (int i = 0; i < array.length; i++) {
            array[i] = new int[i + 1];
        }

        for (int i = 0; i < array.length; i++) {

            //убрал в переменную запрос размера массива, частое обращение
            int arrayLen = array[i].length;

            while (true) {
                long sum = 0;
                //сумма цифр в степени arrayLen-1.

                for (int j = 0; j < arrayLen; j++) {
                    sum+=powsMatrix[array[i][j]][arrayLen-1];
                }
                //убираем расчёт в переменную
                int mathLog10Sum = (int)Math.log10(sum);

                //проверка на совпадение кол-ва цифр в сумме со степенью
                if (mathLog10Sum + 1 == arrayLen) {

                    //как писал автор решения, валидатор проверяет не X<N (X - искомое число),
                    // а кол-во цифр в X должно быть меньше чем в N (т.е., 100<371 (не пройдёт), а 99<371 (а это ок))
                    if (mathLog10Sum < mathLog10N) {

                        //проверка на нарциссизм

                        long tempSum = sum;
                        ArrayList<Integer> arrayList = new ArrayList<>();

                        //выделяем отдельные цифры из sum
                        for (int k = 0; k < arrayLen; k++) {
                            arrayList.add((int)(tempSum % 10 ));
                            tempSum /= 10;
                        }
                        int index = 0;

                        //для array[][] верно [i][j]<=[i][j+1] (грубо говоря, левая цифра <= равна правой ),
                        // нужна сортировка цифр в потенциальном числе Армстр.
                        Collections.sort(arrayList);

                        //проверка совпадения цифр массива array[][] c полученным набором
                        for (Integer entry : arrayList) {
                            if (entry != array[i][index++]) {
                                index = -1;
                                break;
                            }
                        }

                        //если порядок цифр совпал, добавляем в коллекцию
                        if (index > 0) {
                            treeSet.add(sum);
                        }
                    }
                }

                //завершить цикл для i, если старший разряд достиг максимума
                //в данном случае, в числе 123 1-старший разряд, 3 - младший
                // индексы в массиве для этих цифр, 0, 1 и  2 соответственно
                if (array[i][0] == 9) {
                    break;
                }

                //инкремент младшего разряда до достижения максимума
                if (array[i][arrayLen - 1] != 9)
                    array[i][arrayLen - 1]++;
                else{
                    boolean isFind = false;

                    //поиск первого максимума в массиве
                    //при нахождении увеличиваем на 1 старший разряд, а младшему передаём полученное значение
                    //Для чего? -  при обычном инкременте получаем повтор значений. Например: для 2-х разрядных
                    //чисел (00-99), сумма квадратов одинакова для 12, 21 (1*1+2*2). Нужно использовать такие
                    // числа, где меньший разряд всегда больше или равен старшему (или <= ), т.е., 11, 12, 22, 23 (123, 233, 333)
                    //в текущем подходе после чисел 00-09, получаем числа 11-19, 22-29, 33-39 и т.д.
                    for (int j = 0; j < arrayLen - 1; j++) {
                        if (array[i][j + 1] == 9 && !isFind) {
                            array[i][j]++;
                            isFind = true;
                        }
                        //младшему разряду отдаем новое значение старшего
                        if (isFind)
                            array[i][j + 1] = array[i][j];
                    }
                }
            }
        }

        result = new long[treeSet.size()];
        int index = 0;
        for(Long entry : treeSet){
            result[index++] = entry;
        }
        return result;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long N;

        N = Long.MAX_VALUE;
        //N = Integer.MAX_VALUE;
        long result[] = getNumbers(N);

        System.out.printf("Time: %.4f sec%n", (System.currentTimeMillis() - startTime)/1000F);
        System.out.printf("Memory, 1st approach: %.3f Mb%n", (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/Math.pow(2,20));
        System.out.printf("Memory, 2nd approach: %.3f Mb%n", (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()-beforeMemory)/Math.pow(2,20));

        for(long entry: result){
            System.out.println(entry);
        }
    }

    /*
    // 50+ сек. для Integer

    public static long[] getNumbersX(long N) throws IOException {

        long result[] = new long[88];    //64*88/8 = 704 байт;
        long[][] powsMatrix = new long[10][19]; //64*10*19/8 1581 байт
        byte index = 0;

        //Матрица степеней.
        //У Math.pow есть погрешеность в расчёте float-чисел, в т.ч. при возведении в степень (в доках написано))
        //Поэтому только хардкор, только умножение
        for(int i=0;i<10; i++){ //4 байта
            for(int j=0; j<19; j++){  //4 байта
                if(j>0){
                    powsMatrix[i][j] =powsMatrix[i][j-1]*i;
                }
                else {
                    powsMatrix[i][j] =i;
                }
            }
        }

        for(long i=1; i<N; i++){ //8 байт

            boolean stop = false;
            long k = i;  //8 байт
            int count = -1;   //4 байта
            int numbers[] = new int[19];  //32*19/8 76 байт
            long sum =0;  //8 байт
            int pow = 0;   //4 байта
            int zeroCounter = 0;   //4 байта
            int lastNumber = -1;  //4 байта

            while(k>0&&!stop){
                numbers[++count] = (int)(k%10);
                int value = numbers[count];   //4 байта

                if(value == 0) {
                    zeroCounter++;
                    if(count>=1&&zeroCounter==1){
                        lastNumber=numbers[count-1];
                    }
                }

                if(count>0){
                    if(zeroCounter>0){
                        k=k/10;
                        if((k!=0&&value!=0)||(k==0&&value>lastNumber)){
                            stop=true;
                            break;
                        }
                    }
                    else{
                        if(value<=numbers[count-1]){
                            k=k/10;
                        }
                        else {
                            stop=true;
                            break;
                        }
                    }
                }
                else{k=k/10;}
            }

            if(!stop){
                pow=count;
                for(int j=0; j<=count; j++){   //4 байта
                    sum+=powsMatrix[numbers[j]][count];
                }
                k=sum;
                long tempSum = 0;   //8 байт
                while(k>0&&count>=0){
                    tempSum+=powsMatrix[(int)(k%10)][pow];
                    k=k/10;
                }
                if(sum==tempSum&&sum>=i&&(int)Math.log10(sum)<(int)Math.log(N)){
                    result[index++] = sum;
                }
            }
        }

        long tempResult[] = new long[index];
        for(int i=0; i<index;i++){
            tempResult[i]=result[i];
        }
        Arrays.sort(tempResult);
        return tempResult;
    }
     */
}