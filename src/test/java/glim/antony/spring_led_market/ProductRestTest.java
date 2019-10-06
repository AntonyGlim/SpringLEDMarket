package glim.antony.spring_led_market;

import glim.antony.spring_led_market.controllers.ProductsRestController;
import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.services.ProductsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductsRestController.class)
public class ProductRestTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsService productsService;

    @Test
    public void getAllProductsTest() throws Exception {
        List<Product> allProducts = Arrays.asList(
                new Product(1L, "Milk", new BigDecimal(90)),
                new Product(2L, "Bread", new BigDecimal(25)),
                new Product(3L, "Cheese", new BigDecimal(320))
        );

        given(productsService.findAll()).willReturn(allProducts);

        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[0].title", is(allProducts.get(0).getTitle())));
    }
}
