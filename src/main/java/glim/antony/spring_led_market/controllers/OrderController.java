package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Order;
import glim.antony.spring_led_market.entities.User;
import glim.antony.spring_led_market.services.OrderService;
import glim.antony.spring_led_market.services.UserService;
import glim.antony.spring_led_market.services.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createOrder(Principal principal){
        User user = userService.findByPhone(principal.getName());
        Order order = orderService.createOrder(user);
        mailService.sendOrderMail(order);
        return "redirect:/shop";
    }
}
