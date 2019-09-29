package glim.antony.spring_led_market.services;


import glim.antony.spring_led_market.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
