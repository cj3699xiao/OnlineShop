package test.method2;

import org.springframework.stereotype.Component;

@Component  // method 2
public class ServerLogger implements Logger {
    public void log(String info) {
        System.out.println("server log = " + info);
    }
}
