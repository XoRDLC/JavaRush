package com.javarush.task.task20.task2025;


/*
Алгоритмы-числа
*/

import java.util.Arrays;
import java.util.Date;

//не готово
public class Solution {
    // 50+ сек. для Integer

    public static long[] getNumbers(long N) {
        long result[] = new long[88];    //64*88/8 = 704 байт;
        long[][] powsMatrix = new long[10][19]; //64*10*19/8 1581 байт
        byte index = 0;
        for(int i=0;i<10; i++){ //4 байта
            for(int j=0; j<19; j++){  //4 байта
                if(j>0){
                    powsMatrix[i][j] =powsMatrix[i][j-1]*i;//У Math.pow есть погрешеность в расчёте float-чисел, в т.ч. при возведении в степень (в доках написано))
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
                        }
                    }
                    else{
                        if(value<=numbers[count-1]){
                            k=k/10;
                        }
                        else {
                            stop=true;
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
                int newNumbers[] = new int[pow+1];   //4-36 байт
                while(k>0&&count>=0){
                    newNumbers[count--] = (int)(k%10);
                    k=k/10;
                }
                for(int j=0; j<=pow; j++){   //4 байта
                    tempSum+=powsMatrix[newNumbers[j]][pow];
                }
                if(sum==tempSum&&sum>=i){
                    result[index++] = sum;
                }
            }
        }
        long tempResult[] = new long[index];
        for(int i=0; i<index;i++){
            tempResult[i]=result[i];

        }
        Arrays.sort(tempResult);
        result= tempResult;
        return result;
    }

    public static void main(String[] args) {

        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        //long i[] = getNumbers(Integer.MAX_VALUE);
        long i[] = getNumbers((long)Integer.MIN_VALUE*(long)Integer.MIN_VALUE*2-1);
        //long i[] = getNumbers((long)Math.pow(2,63));
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + (afterUsedMem-beforeUsedMem)/1024/1024);
        for(long l: i){System.out.println(l);}
    }

/*

// 106-111 сек. для Integer

    public static long[] getNumbersX(long N) {
       long[] temp = new long[88]; //числа Армстронга, существует всего 88 в 10-й системе
        long time = new Date().getTime();
        long sum = 0;
        byte v = 0;


        long[][] digits = new long[10][19];
        for(int i=0;i<10; i++){
            for(int j=0; j<19; j++){
                if(j==0){
                    digits[i][j] =i;
                }
                else {
                    digits[i][j] =digits[i][j-1]*i;}
            }
        }

        for(long i=1; i<N; i++){
            int powCheck = (int)(Math.log10(i)+1);
            long k= i;

            boolean stop = false;
            while(!stop&&k>0){
                int index = (int)(k%10);
                    long p = digits[index][powCheck-1];
                    if (p > i || sum > i) {
                        stop = true;
                    } else {
                        sum += p;
                        k = k / 10;
                    }
            }
            if(sum==i){
                temp[v++]= sum;
                System.out.println(sum);
            }
            sum=0;
        }
        System.out.println("Time:" + (new Date().getTime()-time ));
        long result[] = new long[v];
        for(int i=0; i<v; i++){
            result[i]=temp[i];}
        return result;
    }
    */
}