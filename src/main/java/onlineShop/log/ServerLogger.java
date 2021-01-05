package onlineShop.log;

//@Component  // method 2
public class ServerLogger implements Logger {
    public void log(String info) {
        System.out.println("server log = " + info);
    }
}
