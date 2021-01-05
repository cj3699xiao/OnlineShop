package test.method3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Application3 {

    public static void main(String[] args) {
        //        with out Spring
        //        Logger logger = new ServerLogger();
        //        PaymentAction paymentAction = new PaymentAction(logger);
        //        paymentAction.pay(new BigDecimal(2));

        // IoC Container = ApplicationContext
        // will create the objects, wire them together, configure them,
        // and manage their complete life cycle from creation till destruction.
        // These objects are called Spring Beans.

//  method 3
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        PaymentAction paymentAction = (PaymentAction) context.getBean("paymentAction");
        paymentAction.pay(new BigDecimal(2));

    }
}
