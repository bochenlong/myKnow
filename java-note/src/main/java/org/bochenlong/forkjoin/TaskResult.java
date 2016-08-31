package org.bochenlong.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Created by bcl on 2016/8/31.
 */
public class TaskResult extends RecursiveTask<Long> {// 用于有返回结果的任务
    private final long[] numbers;
    private final int start;
    private final int end;

    private final static long FORKFLAG = 100000;

    public TaskResult(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public TaskResult(long[] numbers) {
        this.numbers = numbers;
        this.start = 0;
        this.end = numbers.length;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        // 如果任务小于划分标准，则直接计算；
        if (length <= FORKFLAG) {
            return adder();
        }

        // 创建一个子任务来为数组的前一半求和
        TaskResult leftTask = new TaskResult(numbers, start, start + length / 2);
        // 利用另一个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        // 创建一个子任务来为数组的后一半求和
        TaskResult rightTask = new TaskResult(numbers, start + length / 2, end);
        // 同步执行第二个子任务，因为有可能允许进一步递归划分
        // 注意这里rightTask.compute()是为了重用当前线程；如果使用rightTask.fork()则重新分配线程
        Long rightResult = rightTask.compute();
//        rightTask.fork();
        // 读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        return rightResult + leftResult;
//        return rightTask.join()+leftResult;
    }

    private long adder() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
