package co.istad.mbanking.utils;
import java.security.SecureRandom;
public class UtilRandom {
    public static String randomDigit(){
        SecureRandom random = new SecureRandom();
        int randomSixDigit = 100000 + random.nextInt(900000);
        return String.valueOf(randomSixDigit);
    }
}
