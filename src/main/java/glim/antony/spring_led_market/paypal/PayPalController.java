package glim.antony.spring_led_market.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

@Controller
@RequestMapping("/paypal")
public class PayPalController {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

//    @Value("${paypal.mode}")
    private String mode = "sandbox";

    private APIContext apiContext = new APIContext(clientId, clientSecret, mode);

    @GetMapping("/b")
    public String myBuy() {
        return "index";
    }

    @RequestMapping("/buy")
    public String buy(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");
            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl("http://localhost:8188/market/paypal/cancel");
            redirectUrls.setReturnUrl("http://localhost:8188/market/paypal/success");

            Amount amount = new Amount();
            amount.setCurrency("RUB");
            amount.setTotal("1.0");

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setDescription("Покупка в September Market");

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Payment payment = new Payment();
            payment.setPayer(payer);
            payment.setRedirectUrls(redirectUrls);
            payment.setTransactions(transactions);
            payment.setIntent("sale");

            Payment doPayment = payment.create(apiContext);

            Iterator<Links> links = doPayment.getLinks().iterator();

            while (links.hasNext()) {
                Links link = links.next();
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message", "Вы сюда не должны были попасть...");
        return "paypal-result";
    }

    @GetMapping("/success")
    public String success(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            String paymentId = request.getParameter("paymentId");
            String payerId = request.getParameter("PayerID");

            if (paymentId == null || paymentId.isEmpty() || payerId == null || payerId.isEmpty()) {
                return "redirect:/";
            }

            Payment payment = new Payment();
            payment.setId(paymentId);

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);

            Payment executedPayment = payment.execute(apiContext, paymentExecution);

            if (executedPayment.getState().equals("approved")) {
                model.addAttribute("message", "Ваш заказ сформирован");
            } else {
                model.addAttribute("message", "Что-то пошло не так при формировании заказа, попробуйте повторить операцию");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "paypal-result";
    }

    @GetMapping("/cancel")
    public String cancel(Model model) {
        model.addAttribute("message", "Оплата заказа не была проведена. Возможно Вы отменили ее...");
        return "paypal-result";
    }
}