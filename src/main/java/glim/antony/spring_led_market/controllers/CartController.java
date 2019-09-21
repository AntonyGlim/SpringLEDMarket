package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.services.ProductsService;
import glim.antony.spring_led_market.utils.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductsService productsService;

    private Cart cart;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("")
    public String showCart(Model model){
        model.addAttribute("productsInCart", cart.getProductAndCountMap());
        return "cart";
    }

    @GetMapping("/add")
    public String addProductToCart(@RequestParam("id") Long id){
        Product product = productsService.findById(id);
        cart.addProduct(product);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductFromCart(@PathVariable("id") Long id){
        Product product = productsService.findById(id);
        cart.deleteProduct(product);
        return "redirect:/cart";
    }
}
