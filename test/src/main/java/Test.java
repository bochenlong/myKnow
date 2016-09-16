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
        // 开启单线程定时处理任务
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(() -> {
            try {
                System.out.println(LocalTime.now());
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1L, 1L, TimeUnit.SECONDS);
    }
}
