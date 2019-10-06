package glim.antony.spring_led_market.utils;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.repositories.specifications.ProductSpecifications;
import org.springframework.data.jpa.domain.Specification;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ProductsFilter {
    private Specification<Product> specification;
    private StringBuilder filtersString;

    public Specification<Product> getSpecification() {
        return specification;
    }

    public StringBuilder getFiltersString() {
        return filtersString;
    }

    public ProductsFilter(HttpServletRequest request) {
        filtersString = new StringBuilder();
        specification = Specification.where(null);

        if (request.getParameter("word") != null && !request.getParameter("word").isEmpty()) {
            specification = specification.and(ProductSpecifications.titleContains(request.getParameter("word")));
            filtersString.append("&word=" + request.getParameter("word"));
        }

        if (request.getParameter("min") != null && !request.getParameter("min").isEmpty()) {
            specification = specification.and(ProductSpecifications.priceGreaterThanOrEq(new BigDecimal(request.getParameter("min"))));
            filtersString.append("&min=" + request.getParameter("min"));
        }

        if (request.getParameter("max") != null && !request.getParameter("max").isEmpty()) {
            specification = specification.and(ProductSpecifications.priceLesserThanOrEq(new BigDecimal(request.getParameter("max"))));
            filtersString.append("&max=" + request.getParameter("max"));
        }
    }
}
