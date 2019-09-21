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

    private String word;
    private Integer min;
    private Integer max;
    private Integer productsOnPage;
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void setParams(String word, Integer min, Integer max, Integer productsOnPage) {
        this.word = word;
        this.min = min;
        this.max = max;
        this.productsOnPage = productsOnPage;
    }

    public String getFilterStringForURL(){
        StringBuilder stringBuilder = new StringBuilder();
        if (word != null) stringBuilder.append("&word=").append(word);
        if (min != null) stringBuilder.append("&min=").append(min);
        if (max != null) stringBuilder.append("&max=").append(max);
        if (productsOnPage != null) stringBuilder.append("&productsOnPage=").append(productsOnPage);
        return stringBuilder.toString();
    }

    public Page<Product> findAllByPaginAndFiltering(Pageable pageable){
        Specification<Product> spec = Specification.where(null);
        if (word != null) spec = spec.and(ProductSpecifications.titleContains(word));
        if (min != null) spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(min));
        if (max != null) spec = spec.and(ProductSpecifications.priceLesserThanOrEq(max));
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

}
