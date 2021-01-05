package test.method3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// this class is a replacement to payment.xml,  this will become a config file
// Nowadays way
// create @configuration & @bean to manage     // method 3
@Configuration
public class ApplicationConfig {

    @Bean
    public Logger getLogger() {
        return new ServerLogger();
    }

    @Bean
    public PaymentAction paymentAction() {
        return new PaymentAction();
    }
}
