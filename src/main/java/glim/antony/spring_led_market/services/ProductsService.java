package glim.antony.spring_led_market.services;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.repositories.ProductsRepository;
import glim.antony.spring_led_market.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Product> findAllByPaginAndFiltering(Specification<Product>  specification, Pageable pageable){
        return productsRepository.findAll(specification, pageable);
    }
}
