package glim.antony.spring_led_market.errors_hendlers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
