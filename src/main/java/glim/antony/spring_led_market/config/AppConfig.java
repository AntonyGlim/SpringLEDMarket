package glim.antony.spring_led_market.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:private.properties") //for email можем указать много путей и файлов
@ComponentScan("glim.antony.spring_led_market")
public class AppConfig {
}
