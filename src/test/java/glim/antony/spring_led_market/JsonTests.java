package glim.antony.spring_led_market;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.entities.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> json;

    @Autowired
    private JacksonTester<Product> productJacksonTester;

    @Test
    public void testSerialize() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        assertThat(this.json.write(role)).hasJsonPathNumberValue("$.id");
        assertThat(this.json.write(role)).extractingJsonPathStringValue("$.name").isEqualTo("USER");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
        Role realRole = new Role();
        realRole.setId(2L);
        realRole.setName("ADMIN");

        assertThat(this.json.parse(content)).isEqualTo(realRole);
        assertThat(this.json.parseObject(content).getName()).isEqualTo("ADMIN");
    }

    @Test
    public void testSerializeProduct() throws Exception {
        Product product = new Product(55L, "Test product", new BigDecimal(12500));
        assertThat(this.productJacksonTester.write(product)).hasJsonPathNumberValue("$.id");
        assertThat(this.productJacksonTester.write(product)).extractingJsonPathStringValue("$.title").isEqualTo("Test product");
    }

    @Test
    public void testDeserializeProduct() throws Exception {
        String content = "{\"id\": 66,\"title\":\"Test product de serialize\", \"price\": 11111}";
        Product realProduct = new Product(66L, "Test product de serialize", new BigDecimal(11111));

        assertThat(this.productJacksonTester.parse(content)).isEqualTo(realProduct);
        assertThat(this.productJacksonTester.parseObject(content).getTitle()).isEqualTo("Test product de serialize");
    }
}