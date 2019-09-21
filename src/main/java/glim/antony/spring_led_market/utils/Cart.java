package glim.antony.spring_led_market.utils;

import glim.antony.spring_led_market.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS) //для каждого клиента корзина своя
public class Cart {
    private Map<Product, Integer> productAndCountMap;

    public Map<Product, Integer> getProductAndCountMap() {
        return productAndCountMap;
    }

    @PostConstruct // не вмешиваемся в создание бина, поэтому не лезем в конструктор
    public void init(){
        productAndCountMap = new HashMap<>();
    }

    public void addProduct(Product product){
        if (productAndCountMap.containsKey(product)){
            Integer count = productAndCountMap.get(product);
            productAndCountMap.put(product, count + 1);
        } else {
            productAndCountMap.put(product, 1);
        }
    }


}
