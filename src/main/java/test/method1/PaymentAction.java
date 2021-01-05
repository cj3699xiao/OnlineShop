package test.method1;

import java.math.BigDecimal;

public class PaymentAction {
    // Autowired from spring to support inject variable to logger
    // Since we don't care about logger, we just want to use method with that class

    private Logger logger;

    // if we don't use @Autowired, we have to inject it here   // 1
    public PaymentAction(Logger logger) {
        this.logger = logger;
    }

    public void pay(BigDecimal payValue) {
        logger.log("pay begin, payValue is " + payValue);
        logger.log("pay end");
    }
}
