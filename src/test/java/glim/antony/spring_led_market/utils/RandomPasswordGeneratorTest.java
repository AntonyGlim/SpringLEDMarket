package glim.antony.spring_led_market.utils;

import org.junit.Test;

public class RandomPasswordGeneratorTest {
    RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();

    @Test
    public void simpleTest(){
        for (int i = 0; i < 10; i++) {
            System.out.println(randomPasswordGenerator.createPassword());
        }
    }
}
