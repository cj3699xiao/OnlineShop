package test.method2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component  //method 2
public class PaymentAction {
    // Autowired from spring to support inject variable to logger
    // Since we don't care about logger, we just want to use method with that class

    // method 2
    @Autowired
    private Logger logger;


    public void pay(BigDecimal payValue) {
        logger.log("pay begin, payValue is " + payValue);
        logger.log("pay end");
    }
}
