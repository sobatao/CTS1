package Utils;

/**
 * Created by Meaks on 12/5/2015.
 */
public class ExceptionMailer {
    public static void send(Throwable e) {
        System.out.println("Sending email containing exception " + e);
    }

    public static void log(String msg) {
        System.out.println("Interceptor: " + msg);
    }
}
