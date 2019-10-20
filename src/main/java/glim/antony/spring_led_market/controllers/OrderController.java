package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Order;
import glim.antony.spring_led_market.entities.User;
import glim.antony.spring_led_market.services.OrderService;
import glim.antony.spring_led_market.services.UserService;
import glim.antony.spring_led_market.services.email.MailService;
import glim.antony.spring_led_market.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    private UserService userService;
    private MailService mailService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/create")
    public String createOrder(Principal principal,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "address") String address)
    {
        User user = null;
        if (principal != null){
            user = userService.findByPhone(principal.getName());
        } else { //если пользователя нет в базе
            if (userService.isUserExist(phone)){
                user = userService.findByPhone(phone);
            } else {
                SystemUser systemUser = new SystemUser();
                systemUser.setPhone(phone);
                systemUser.setFirstName(firstName);
                user = userService.save(systemUser);
            }
        }
        Order order = orderService.createOrder(user, phone, address); //телефон указанный именно здесь, а не принадлежащий пользователю в БД
        if (user.getEmail() != null){
            mailService.sendOrderMail(order);
        }
        return "redirect:/shop";
    }
}
