package org.example.util;

import java.util.Random;

public class RandomPicker {

    private static Random random = new Random();
    public static Integer getRandomNumberNot0(Integer range){

        Integer randomNumber = random.nextInt(range);

        if(randomNumber == 0){
            randomNumber = random.nextInt(range);
        }
        return randomNumber;
    }

    public static Integer getRandomNumber(Integer range){

        Integer randomNumber = random.nextInt(range);

        if(randomNumber == 0){
            randomNumber = random.nextInt(range);
        }
        return randomNumber;
    }


}
