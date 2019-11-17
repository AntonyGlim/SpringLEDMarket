package glim.antony.spring_led_market.repositories;

import glim.antony.spring_led_market.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByPhone(String phone);
    boolean existsByPhone(String phone);
}
