package glim.antony.spring_led_market.services;


import glim.antony.spring_led_market.entities.User;
import glim.antony.spring_led_market.utils.SystemUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByPhone(String username);
    User save(SystemUser systemUser);
}
