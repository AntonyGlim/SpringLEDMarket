package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.errors_hendlers.ResourceNotFoundException;
import glim.antony.spring_led_market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/v1/products")
public class ProductsRestController {

    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/") //http://localhost:8189/market/rest/v1/products/
    @ResponseStatus(HttpStatus.OK)
    public List<Product> showProducts(){
        return productsService.findAll();
    }

    @GetMapping("/{id}") //http://localhost:8189/market/rest/v1/products/2
    @ResponseStatus(HttpStatus.OK)
    public Product showProductById(@PathVariable(name = "id") Long id){
        Product product = productsService.findById(id);
        if (product == null) throw new ResourceNotFoundException("Product not found. (id = " + id + ")");
        return product;
    }

    @PostMapping("/") //http://localhost:8189/market/rest/v1/products/
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product product){
        product.setId(null); //защита
        return productsService.save(product);
    }

    @PutMapping("/")
    public Product updateProduct(@RequestBody Product product){
//        if (product.getId() == null) throw new ...Exception("Product have no id"); //защита
        return productsService.save(product);
    }

    @PutMapping("/{id}")
    public Product saveOrUpdate(@RequestBody Product newProduct, @PathVariable Long id){
        Product product = productsService.findById(id);
        if (product != null){
            product.setTitle(newProduct.getTitle());
            product.setCost(newProduct.getCost());
        } else {
            product = newProduct;
            product.setId(id);
        }
        return productsService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productsService.deleteById(id);
    }


}
