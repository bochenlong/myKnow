package org.bochenlong.forkjoin;


import org.bochenlong.TimeUt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Created by bcl on 2016/8/31.
 */
public class ForJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();
        long t = TimeUt.currT();
        ForkJoinTask forkJoinTask = forkJoinPool.submit(new TaskResult(numbers));
        System.out.println(forkJoinTask.get());
        TimeUt.useTP(t);
        long sum = 0;
        t = TimeUt.currT();
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        System.out.println(sum);
        TimeUt.useTP(t);
    }
}
