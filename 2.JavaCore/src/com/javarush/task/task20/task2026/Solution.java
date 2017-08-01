package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    //какое-то топорное решение, повтор условий, брутфорс.. брр
    //вернуться при наличии времени


    public static int getRectangleCount(byte[][] a) {
        int recCount =0;
        for(int i=0; i<a.length;i++){
            for(int j=0; j<a[i].length; j++){
                if(a[i][j]==1){
                    if(i==0&&j==0){
                        recCount++;
                    }
                    else if(i==0&&j>0){
                        if(a[i][j-1]==0)recCount++;

                    }
                    else if(j==0&&i>0){
                        if(a[i-1][j]==0)recCount++;
                    }
                    else if(a[i-1][j]+a[i][j-1]+a[i-1][j-1]==0) recCount++;
                }
            }
        }
        return recCount;
    }
}
