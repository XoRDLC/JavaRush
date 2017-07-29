package com.javarush.task.task14.task1408;

/**
 * Created by Dimitriy on 20.05.2017.
 */
public class RussianHen extends Hen {
    private int eggsCount = 136;

    public int getCountOfEggsPerMonth() {
        return eggsCount;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + eggsCount + " яиц в месяц.";
    }
}
