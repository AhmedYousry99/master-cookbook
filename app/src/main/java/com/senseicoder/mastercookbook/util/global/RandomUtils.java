package com.senseicoder.mastercookbook.util.global;

import java.util.Random;

public class RandomUtils {

    public static String getRandomLowercaseLetter() {
        Random random = new Random();
        return String.valueOf((char) (random.nextInt(26) + 'a'));
    }

}
