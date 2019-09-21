package glim.antony.spring_led_market.services;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.repositories.ProductsRepository;
import glim.antony.spring_led_market.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    String word;
    Integer min;
    Integer max;
    Integer pageNumber;

    ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Product> findAllByPaginAndFiltering(Pageable pageable){
        Specification<Product> spec = Specification.where(null);
        if (word != null){
            spec = spec.and(ProductSpecifications.titleContains(word));
        }
        if (min != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(min));
        }
        if (max != null){
            spec = spec.and(ProductSpecifications.priceLesserThanOrEq(max));
        }
        return productsRepository.findAll(spec, pageable);
    }

    public Product save(Product product){
        return productsRepository.save(product);
    }

    public Product findById(Long id){
        return productsRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList();
        productsRepository.findAll().forEach(list::add);
        return list;
    }

    public void setParams(String word, Integer min, Integer max, Integer pageNumber) {
        this.word = word;
        this.min = min;
        this.max = max;
        this.pageNumber = pageNumber;
    }

    public String getFilterStringForURL(){
        StringBuilder stringBuilder = new StringBuilder();
        if (word != null){
            stringBuilder.append("&word=" + word);
        }
        if (min != null) {
            stringBuilder.append("&min=" + min);
        }
        if (max != null){
            stringBuilder.append("&max=" + max);
        }
        return stringBuilder.toString();
    }
}
