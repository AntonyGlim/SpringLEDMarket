package glim.antony.spring_led_market.services.email;


import glim.antony.spring_led_market.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailMessageBuilder {
    private TemplateEngine templateEngine; //обработчик таймлифа

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    //строит письмо
    public String buildOrderEmail(Order order) {
        Context context = new Context();
        context.setVariable("order", order); //context здесь что-то вроде model
        return templateEngine.process("order-mail", context);
    }

    public String buildOrderEmailWithPassword(Order order, String password) {
        Context context = new Context();
        context.setVariable("order", order); //context здесь что-то вроде model
        context.setVariable("password", password); //context здесь что-то вроде model
        return templateEngine.process("order-mail", context);
    }
}
