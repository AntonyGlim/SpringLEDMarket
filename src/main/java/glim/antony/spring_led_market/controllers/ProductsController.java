package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.repositories.specifications.ProductSpecifications;
import glim.antony.spring_led_market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {

    ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String showProducts(Model model,
                               @RequestParam(name = "word", required = false) String word,
                               @RequestParam(name = "min", required = false) Integer min,
                               @RequestParam(name = "max", required = false) Integer max,
                               @RequestParam(name = "pageNumber", required = false) Integer pageNumber)
    {
        productsService.setParams(word, min, max, pageNumber);

        //TODO transfer Specification to service

        if (pageNumber == null){
            pageNumber = 1;
        }

        Page<Product> page = productsService.findAllByPaginAndFiltering(PageRequest.of(pageNumber - 1, 5, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        model.addAttribute("word", word);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("filters", productsService.getFilterStringForURL());
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        Product product = productsService.findById(id);
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/edit")
    public String saveModifiedProduct(@ModelAttribute(name = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id){
        productsService.deleteById(id);
        return "redirect:/products";
    }

}

