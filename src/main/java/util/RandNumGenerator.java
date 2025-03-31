package util;

import java.util.Random;

public class RandNumGenerator {
    public static int randInt (int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
