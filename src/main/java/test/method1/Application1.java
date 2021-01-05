package test.method1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class Application1 {
    // use XML on resource 3method test

    public static void main(String[] args) {
        //        with out Spring
        //        Logger logger = new ServerLogger();
        //        PaymentAction paymentAction = new PaymentAction(logger);
        //        paymentAction.pay(new BigDecimal(2));

        // IoC Container = ApplicationContext
        // will create the objects, wire them together, configure them,
        // and manage their complete life cycle from creation till destruction.
        // These objects are called Spring Beans.

        // this way is use payment.xml  // method 1  && 2
        ApplicationContext container = new ClassPathXmlApplicationContext("3methodtest.xml");
        // Loc container
        PaymentAction paymentAction = (PaymentAction) container.getBean("paymentAction");
        paymentAction.pay(new BigDecimal(2));

////  method 3
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        //
//        PaymentAction paymentAction = (PaymentAction) context.getBean("paymentAction");
//        paymentAction.pay(new BigDecimal(2));

    }
}
