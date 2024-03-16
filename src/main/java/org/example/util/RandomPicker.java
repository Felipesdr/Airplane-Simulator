package org.example.util;

import java.util.Random;

public class RandomPicker {

    private static Random random = new Random();
    public static Integer getRandomNumberNot0(Integer range){

        int randomNumber = random.nextInt(range);

        if(randomNumber == 0){
            randomNumber = random.nextInt(range);
        }
        return randomNumber;
    }

    public static Integer getRandomNumber(Integer max){

        int min = -1;

        max = max -1;

        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);

        return random_int;
    }


}
