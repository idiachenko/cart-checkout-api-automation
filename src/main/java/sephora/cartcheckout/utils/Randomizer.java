package sephora.cartcheckout.utils;

import java.util.Random;

public class Randomizer {
    static Random random = new Random();

    public static String generateSlug(){
        return "P" + random.nextInt(999999);
    }
    public static String generateSkuNumber(){
        return String.valueOf(random.nextInt(999999));
    }

    public static Integer generatePrice(){
        return random.nextInt(999999);
    }
}
