**План:**  
* разбор ДЗ  
* Как подключить RabbitMQ к Spring  
* Наш магазин как кукБук  
* Тема Spring тестирование (принципы, аннотации, подключение к БД)  
* Как подключится к ВК на RESTTemplate  
  
**0:04** Вопрос про связь микросервисов    
**0:11** подключение к ВК  
**0:12** Зайти в раздел для разработчиков получить клиент и сикрет. (общение через RestTemplate)  
**0:16** Запуска приложения  
**0:17** Разбор того что происходит по шагам  
**0:18** мы в начале получаем токен, а потом обращаемся к VK. Используем JsonParser  
**0:23** Можем токен положить в сессию, чтобы каждый раз не вводить  
**0:25** Тесты  
**0:26** Подключаем тесты в поме и hamcrest для продвинутого тестирования. h2database для тестирования тестов  
**0:27** Список того, что необходимо для проекта JUnit, Spring Test & Spring Boot Test, AssertJ, Hamcrest, Mockito, JSONassert, JsonPath  
**0:30** Аннотации @SpringBootTest и RunWith  
**0:31** Тестирование корзины (сервер не запускается, если не укжем спец. параметр)  
**0:33** Запуск всего сервака и стучимся на endPoint  
**0:34** Папка testMigration - для запуска базы  
**0:35** Класс TestRestTemplate  
**0:36** @JsonTest - для проверки сериализации и десереализации (класс JacksaonTester)  
**0:40** @AutoConfigureMockMvc - как эмуляция сервера (класс MockMvc) cтучим на адресс и проверяем  
**0:43** @WithMockUser и @WithAnonimousUser - создание фиктивного пользователя  
**0:45** Проверка куда перекинет залогиненного  
**0:49** Исправил ошибку в тесте  
**0:49** Тестирование только слоя контроллеров - не работает а тянет безопасность Тестирование отдельных элементов (например сервисы) Используем Моки (@MockBean)   
**1:00** Моки можно создават из объектов  
**1:02** @Spy (из библиотеки макито) - очень похоже на мок. Отличается от Мок тем, что можем подменять проверяемый о-кт. (ПОЧИТАТЬ)  
  
**1:05 - 1:11** Перерыв  
  
**1:11 - 1:15** Поиск бага  
**1:15** Тестирование RESTController  
**1:17** JsonPath. символ $ - это ссылка на самого себя  
**1:20** Ссылка на JsonPath  
**1:21** Тестирования работы с ДБ (в нашем случае H2) Аннотация @DataJpaTest. Все происходит в рамках транзакций. Чтобы отменить - спец. аргумент в анотации   
**1:24** TestEntityManager - хорошо работает с FlyWay  
**1:25** Для работы напрямую - своя аннотация  
**1:27** Настройка inMemoruDataBase т.к. диалекты чуть разные. проперрти в ресурсах тестов.   
**1:28** режим совместимости с постгрес и настройка FlyWay  
**1:31** Как прописать откуда брать настройки тестов  
**1:32** Закончили тесты   
**1:33** Автоматизация тестирования (тестирование снаружи)  
**1:34** Создание отдельного пакета тестов  
**1:35** selenium; Эмулятор браузера (драйвер для хрома)  
**1:37** тест через браузер  
**1:38** Получить всю страницу и найти на ней какой-то текст  
**1:40** Тестируем заход на сайт. Для тестировщиков добавляем атрибут id, чтобы им было легче найти  
**1:44** Создали родительский класс и вынесли все общее в него  
**1:45** Находим элементы по cssSelector (по id) и устанавливаем в него данные, а кнопке делаем click. ТАК НИКТО НЕ ДАЛАЕТ))   
**1:48** Создание фреймворка - для разбивки на отдельные действия на странице. Типо набор заранее определенных стандартных действий  
**1:52** Вынесение селекторов в константы (класс By) сам By это критерий для поиска докуменов  
**1:56** id для кнопки "Выйти"  
**2:02** Время ожидания добавили (Есть общее время и частое)  
**2:05** Тест прошел  
**2:06** Для страниц пишем пейжи и работаем через них  
**2:06** Про класс WebDriverWait ждем пока ... не более ... секунд  
**2:13** ДЗ - написать 5 - 10 тестов для маркета  
  
**Доп. инфо.**
JUnit: The de-facto standard for unit testing Java applications.  
Spring Test & Spring Boot Test: Utilities and integration test support for Spring Boot applications.  
AssertJ: A fluent assertion library.  
Hamcrest: A library of matcher objects (also known as constraints or predicates).  
Mockito: A Java mocking framework.  
JSONassert: An assertion library for JSON.  
JsonPath: XPath for JSON.  

**Какие тесты написать для ДЗ:**  
1. Тест через браузер  
2. Тест БД  
3. Тест RESTController-а  
4. Тест куда перекинет залогиненного  
5. Тест отдельных элементов (например сервисы) Используем Моки  

```markdown
Тесты на проверку сериализации:

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
```  
Тесты Мок: MockMVCTest.java  
```markdown
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test //есть-ли на том адресе на который обращаемся на странице такие слова
    public void tryToStart() throws Exception{
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("интернет-магазин")));
    }

    @Test //есть-ли на том адресе на который обращаемся на странице такие слова
    public void tryToLoadCart() throws Exception{
        mockMvc.perform(get("/cart"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Корзина")));
    }

    @Test //стучим на админ, знаем что не должно нас пустить но должно перекинуть на определенный адрес
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLogin() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("admin").password("100"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void badCredentials() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("moo").password("moo"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void authTest() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("88888888").password("100"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(username = "+79380000000", roles = "ADMIN")
    // @WithAnonymousUser
    public void securityAccessAllowedTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
```  

Модульное тестирование с подменой репозитория
```markdown
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void findOneUserTest() {
        Mockito.doReturn(new User("22222222", "22222222", "firstName", "lastName", "em@em.com"))
                .when(userRepository)
                .findOneByPhone("22222222");
        User user = userService.findByPhone("22222222");
        Assert.assertNotNull(user);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.eq("22222222"));
        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.anyString());

    }
}
```  

Тест со шпионом:  
```markdown
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpyTest {
    @Spy
    private List<Integer> spiedList = new ArrayList<>();

    @Spy
    private Product product = new Product();

    @Test
    public void spyTest() {
        spiedList.add(1);
        spiedList.add(2);
        spiedList.add(3);

        Mockito.verify(spiedList).add(1);
        Mockito.verify(spiedList).add(2);
        Mockito.verify(spiedList).add(3);

        assertEquals(3, spiedList.size());

        Mockito.doReturn(100).when(spiedList).size();

        assertEquals(100, spiedList.size());
    }

    @Test(expected = ArithmeticException.class)
    public void mockThrow() {
        List<String> listMock = Mockito.mock(List.class);
        when(listMock.add(anyString())).thenThrow(ArithmeticException.class);
        listMock.add("Hello");
    }

    @Test
    public void productSpyTest(){
        product.setTitle("Тест");
        Mockito.verify(product).setTitle("Тест");
        Mockito.doReturn("Подмена").when(product).getTitle();
        assertEquals("Подмена", product.getTitle());
    }
}
```
