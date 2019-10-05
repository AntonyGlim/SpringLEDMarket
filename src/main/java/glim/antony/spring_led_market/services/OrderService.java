package glim.antony.spring_led_market.services;

import glim.antony.spring_led_market.entities.Order;
import glim.antony.spring_led_market.entities.User;
import glim.antony.spring_led_market.repositories.OrderRepository;
import glim.antony.spring_led_market.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private Cart cart;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public OrderService(OrderRepository orderRepository, Cart cart) {
        this.orderRepository = orderRepository;
        this.cart = cart;
    }

    public void createOrder(User user) {
        Order order = new Order(user);
        cart.getItems().values().stream().forEach(i -> order.addItem(i));
        cart.clear();
        orderRepository.save(order);
    }
}
