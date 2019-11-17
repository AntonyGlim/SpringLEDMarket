package glim.antony.spring_led_market.repositories.specifications;

import glim.antony.spring_led_market.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> titleContains(String word) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Product> priceGreaterThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
        };
    }

    public static Specification<Product> priceLesserThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), value);
        };
    }

    public static Specification<Product> categoryId(Long id) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("category").get("id"), id);
        };
    }
}
