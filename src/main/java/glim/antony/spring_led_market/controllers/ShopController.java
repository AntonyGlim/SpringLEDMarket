package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.services.ProductsService;
import glim.antony.spring_led_market.utils.ProductsFilter;
import glim.antony.spring_led_market.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.Optional;

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
    ) {
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

    @GetMapping("/{id}")
    public String showSimpleProductPage(
            Model model,
            @PathVariable(name = "id") Long id,
            @CookieValue(value = "lastProducts", required = false) String lastProducts,
            HttpServletResponse response
    ) {
        Product product = productsService.findById(id);

        if (lastProducts == null) lastProducts = String.valueOf(product.getId());
        else lastProducts = lastProducts + "q" + product.getId();

        //TODO гораздо лучше это реализовал Фисунов в 28 1:19
        LinkedList<String> lastProductsIndexesList = Utils.cutVisitedProductsHistory(lastProducts, 5);
        response.addCookie(new Cookie("lastProducts", Utils.listToString(lastProductsIndexesList)));
        LinkedList<Product> productsList = new LinkedList<>();

        for (String s : lastProductsIndexesList) productsList.add(productsService.findById(Long.parseLong(s)));

        model.addAttribute("product", product);
        model.addAttribute("productsList", productsList);
        return "simple_product";
    }

    @GetMapping("/back")
    public String returnPreviousPage(HttpServletRequest request) {
        return "redirect:/shop";
    }

}
