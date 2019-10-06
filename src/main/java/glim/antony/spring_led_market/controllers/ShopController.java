package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.services.ProductsService;
import glim.antony.spring_led_market.utils.ProductsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String shop(
            Model model,
            HttpServletRequest request,
            HttpServletResponse response,
            @CookieValue(name = "productsOnPage", required = false) Integer productsOnPage,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber
            // @RequestParam Map<String, String> params
    ){
        ProductsFilter productsFilter = new ProductsFilter(request);
        if (productsOnPage == null) {
            productsOnPage = 5;
            response.addCookie(new Cookie("pageNumber", String.valueOf(productsOnPage)));
        }
        if (pageNumber == null || pageNumber < 1) pageNumber = 1;
        Page<Product> page = productsService.findAllByPagingAndFiltering(productsFilter.getSpecification(), PageRequest.of(pageNumber - 1, 10, Sort.Direction.ASC, "id"));
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("filters", productsFilter.getFiltersString());
        model.addAttribute("page", page);
        return "shop";
    }
}
