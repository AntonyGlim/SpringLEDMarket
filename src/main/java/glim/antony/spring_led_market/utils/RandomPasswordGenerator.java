package glim.antony.spring_led_market.utils;

import java.util.Random;
import java.util.stream.Collectors;

public class RandomPasswordGenerator {
    public static String createPassword(){
        return new Random()
                .ints(8, 33, 122)
                .mapToObj(i -> String.valueOf((char)i))
                .collect(Collectors.joining());
    }
}
