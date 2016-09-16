import org.bochenlong.print.PrintUt;
import org.bochenlong.time8.TimeUt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bochenlong on 16-9-13.
 */
public class Test {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("I am full");
        long t = TimeUt.currT();
        for (int i = 0; i < 100; i++) {
            System.out.println("t" + i);
            new Thread(r).start();
        }
        TimeUt.useTP(t);
        t = TimeUt.currT();
        for (int i = 0; i < 100; i++) {
            System.out.println("s" + i);
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(r);
            service.shutdown();
        }
        TimeUt.useTP(t);
    }

}
