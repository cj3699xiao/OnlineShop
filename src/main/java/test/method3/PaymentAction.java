package test.method3;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class PaymentAction {
    // Autowired from spring to support inject variable to logger
    // Since we don't care about logger, we just want to use method with that class

    // method 3, we have to inject here!
    @Autowired
    private Logger logger;


    public void pay(BigDecimal payValue) {
        logger.log("pay begin, payValue is " + payValue);
        logger.log("pay end");
    }
}
