package org.bochenlong.parallel;

import org.bochenlong.time8.TimeUt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

/**
 * Created by bochenlong on 16-10-8.
 */
public class PExec {

    /**
     * @param pc   并行数
     * @param ac   执行总数
     * @param task 任务
     */
    public static void exec(int pc, int ac, Callable task) {
        ExecutorService executorService = Executors.newFixedThreadPool(pc);
        LongAdder longAdder = new LongAdder();
        ac = ac < pc ? pc : ac;
        List<Future> fs = new ArrayList<>(ac);
        long t = TimeUt.currTPT();
        for (int i = 0; i < ac; i++) {
            Future f = executorService.submit(task);
            fs.add(f);
        }

        for (Future f : fs) {
            try {
                f.get(30, TimeUnit.MINUTES);
                longAdder.increment();
                System.out.println(longAdder.longValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        long t1 = TimeUt.useT(t) / 1000;
        System.out.println("总计用时：" + t1);
        System.out.println("总计处理：" + ac);
        System.out.println("平均每秒处理：" + (float) ac / t1);
        System.out.println("平均每条数据处理：" + (float) t1 / ac);
        executorService.shutdown();
    }

}
