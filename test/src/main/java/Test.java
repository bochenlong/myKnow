import org.bochenlong.print.PrintUt;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by bochenlong on 16-9-13.
 */
public class Test {
    public static void main(String[] args) {
        try {
            String string = null;
            System.out.println(string.length());
        } finally {
            System.out.println("hello world");
        }
    }
}
