package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) — последней.
text — это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании.


*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        //"home", "same", "vor","gae","oprek", "plg", "lngro",
        //"fsgr", "fulm", "fder", "klre","kovh", "kerp", "pmlu", "poee", "plgm", "jjeeo", "jhvok", "jrrad"
        List<Word> words = detectAllWords(crossword, "fsgr", "fulm", "fder", "klre","kovh", "kerp", "pmlu", "poee", "plgm", "jjeeo", "jhvok", "jrrad");
        for(Word list:words) System.out.println(list);

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    //клиника, а не решение. Даже не хочу комментировать
    //но надо, потом хрен разберусь.

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        //перебор слов
        for (String word : words) {
            char[] letters = word.toCharArray();
            int startY = 0;
            int startX = -1;
            boolean isFound = false;

            //поскольку слово 100% есть в массиве, объявляем и инициализируем сразу переменную.
            Word objWord = new Word(word);

            //слева-направо, по строчкам, ищем совпадение первой буквы в слове.
            //если нашли - проверяем с каких сторон доступно нужное кол-во букв,
            //проверяем оставшиеся стороны на совпадение оставшихая букв в слове.

            for (int i = startY; i < crossword.length; i++) {
                isFound = false;
                //поиск совпадения первой буквы
                for (int j = startX + 1; j < crossword[i].length; j++) {
                    startX = j;
                    startY = i;
                    if (crossword[i][j] == letters[0]) {
                        isFound = true;
                        break;
                    }
                }

                //буква найдена - ищем в каком направлении поместится всё слово
                if (isFound) {
                    //кол-во не найденных в слове букв
                    int lenLetters = letters.length - 1;

                    //проверяем в каком направлении влезет всё слово
                    boolean left = startX - lenLetters >= 0;
                    boolean up = startY - lenLetters >= 0;
                    boolean right = startX + lenLetters < crossword[i].length;
                    boolean down = startY + lenLetters < crossword.length;

                    //в цикле будем проверять одну сторону и одну диагональ. Где найдется слово, тот перключатель и сработает
                    boolean diagonal = false;
                    boolean planar = false;

                    //вставляем начальные координаты
                    objWord.setStartPoint(startX, startY);

                    //простыня с проверкой сторон и диагоналей
                    //разбито по парам направлений - З, СЗ; С, СВ; В, ЮВ; Ю, ЮЗ.
                    //по-хорошему, проверка на доступность одного и того же направления проверяется в нескольких местах,
                    //думаю, можно объеденить.
                    //смысла от этой задачи никакого (задача ради задачи), а времени убито - куча. Но, для самоуспокоения:
                    //todo: подумать над сокращением кода и оптимизацией при наличии времени
                    if(left){
                        planar = true;
                        //если кол-во букв хватает слева и сверху от найденной первой буквы, то и СЮ-диагональ тоже подходит
                        diagonal = up;

                        //отбрасываем сторону или диагональ при первом расхождении
                        for(int X=startX-1;X>=startX-lenLetters; X--){
                            if(planar&&crossword[i][X]!=letters[startX - X]){
                                planar = false;}

                            if(diagonal&&crossword[i-(startX-X)][X]!=letters[startX - X]){
                                diagonal = false;}
                        }
                        //если все буквы совпали - заносим последние координаты.
                        //Смешно, но  возможна ситуация, когда слово может быть найдено и в линии и в диагонали.
                        //Поскольку нет оговорок в условии, то в блоке координаты будут от диагонали
                        //в проверке всех сторон - координаты от первого найдееного блока
                        if(planar){
                            objWord.setEndPoint(startX-lenLetters, i);
                        }
                        if(diagonal){
                            objWord.setEndPoint(startX-lenLetters, startY-lenLetters);
                        }
                        //слово найдено - выход
                        //по хорошему, незачем делать повторную проверку на true. Но так выглядит симпатичнее
                        if(planar||diagonal) break;
                    }

                    //всё тоже самое, теперь для пары направлений - С, СВ
                    if(up){
                        planar = true;
                        diagonal = right;
                        for(int Y=startY-1;Y>=startY-lenLetters; Y--){
                            if(planar&&crossword[Y][startX]!=letters[startY - Y])
                                planar = false;

                            if(diagonal&&crossword[Y][startX+startY-Y]!=letters[startY - Y]){
                                diagonal = false;}
                        }
                        if(planar){
                            objWord.setEndPoint(startX, startY-lenLetters);
                        }
                        if(diagonal){
                            objWord.setEndPoint(startX+lenLetters, startY-lenLetters);
                        }
                        if(planar||diagonal) break;
                    }
                    //всё тоже самое, теперь для пары направлений - В, ЮВ
                    if(right){
                        planar = true;
                        diagonal = down;
                        for(int X=startX+1;X<=startX+lenLetters; X++){
                            if(planar&&crossword[i][X]!=letters[X-startX])
                                planar = false;

                            if(diagonal&&crossword[i+(X-startX)][X]!=letters[X-startX]){
                                diagonal = false;}
                        }
                        if(planar){
                            objWord.setEndPoint(startX+lenLetters, i);
                        }
                        if(diagonal){
                            objWord.setEndPoint(startX+lenLetters, startY+lenLetters);
                        }
                        if(planar||diagonal) break;
                    }
                    //всё тоже самое, теперь для пары направлений - Ю, ЮЗ
                    if(down){
                        planar = true;
                        diagonal = left;
                        for(int Y=startY+1;Y<=startY+lenLetters; Y++){
                            if(planar&&crossword[Y][startX]!=letters[Y - startY])
                                planar = false;

                            if(diagonal&&crossword[Y][startX-Y+startY]!=letters[Y - startY]){
                                diagonal = false;}
                        }
                        if(planar){
                            objWord.setEndPoint(startX, startY+lenLetters);
                        }
                        if(diagonal){
                            objWord.setEndPoint(startX-lenLetters, startY+lenLetters);
                        }
                        if(planar||diagonal) break;
                    }
                }
                //вот нахрена я вынес из цикла. Теперь и проверка нужна на достижение максимума по Х
                if(startX+1==crossword[i].length) startX=-1;
                else{i--;}
            }
            //финита, слово найдено, слово добавлено
            if(isFound)list.add(objWord);
        }
        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}


        /*
        StringBuffer horizontal[] = new StringBuffer[crossword.length];
        StringBuffer vertical[] = new StringBuffer[crossword[0].length];
        StringBuffer leftDiagonal[] = new StringBuffer[crossword.length+crossword[0].length-1];
        StringBuffer rightDiagonal[] = new StringBuffer[crossword.length+crossword[0].length-1];
        for(int i = 0; i<crossword.length; i++){
            for(int j =0; j<crossword[i].length; j++){
                char c = (char)crossword[i][j];
                if(horizontal[i]==null) horizontal[i]=new StringBuffer();
                if(vertical[j]==null) vertical[j]=new StringBuffer();
                if(leftDiagonal[crossword[i].length-1+j-i]==null) leftDiagonal[crossword[i].length-1+j-i]=new StringBuffer();
                if(rightDiagonal[i+j]==null) rightDiagonal[i+j]=new StringBuffer();

                horizontal[i].append(c);
                vertical[j].append(c);
                leftDiagonal[crossword[i].length-1+j-i].append(c);
                rightDiagonal[i+j].append(c);
            }
        }
        */