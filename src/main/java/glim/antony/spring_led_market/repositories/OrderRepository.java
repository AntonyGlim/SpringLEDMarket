package glim.antony.spring_led_market.repositories;

import glim.antony.spring_led_market.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
