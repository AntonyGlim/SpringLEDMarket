package glim.antony.spring_led_market;

import glim.antony.spring_led_market.entities.User;
import glim.antony.spring_led_market.repositories.UserRepository;
import glim.antony.spring_led_market.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void findOneUserTest() {
        Mockito.doReturn(new User("22222222", "22222222", "firstName", "lastName", "em@em.com"))
                .when(userRepository)
                .findOneByPhone("22222222");
        User user = userService.findByPhone("22222222");
        Assert.assertNotNull(user);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.eq("22222222"));
        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.anyString());

    }
}
