package org.bochenlong;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bochenlong on 16-11-22.
 */
public class TimerUtil {

    private static String DEFAULT_GROUP = "default";

    private static class holder {
        private static TimerUtil timerUtil = new TimerUtil();
    }

    private Vector<Task> timers;


    public static TimerUtil getInstance() {
        return holder.timerUtil;
    }

    private TimerUtil() {
        timers = new Vector<>();
    }

    public synchronized void register(String key, TimerTask timerTask,
                                      long delay, long period) {
        Optional<Task> optional = findTask(DEFAULT_GROUP, key);

        if (!optional.isPresent()) {
            Task task = new Task(DEFAULT_GROUP, key, timerTask, delay, period);
            timers.add(task);
        }
    }

    public synchronized void register(String group, String key, TimerTask timerTask,
                                      long delay, long period) {
        Optional<Task> optional = findTask(group, key);

        if (!optional.isPresent()) {
            Task task = new Task(group, key, timerTask, delay, period);
            timers.add(task);
        }
    }

    public synchronized void runNow(String key, TimerTask timerTask,
                                    long delay, long period) {
        runNow(DEFAULT_GROUP, key, timerTask, delay, period);
    }

    public synchronized void runNow(String group, String key, TimerTask timerTask,
                                    long delay, long period) {
        Optional<Task> optional = findTask(group, key);

        if (optional.isPresent()) {
            Task task = optional.get();
            if (!task.isRunning()) task.run();
        } else {
            Task task = new Task(group, key, timerTask, delay, period);
            task.run();
            timers.add(task);
        }
    }

    public synchronized void startTimer(String group, String key) {
        Optional<Task> optional = findTask(group, key);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.run();
        }
    }

    public synchronized void startGroupTimer(String group) {
        findGroupTask(group).ifPresent(a -> a.stream().forEach(Task::run));
    }

    public synchronized void startAllTimer() {
        findAllTask().ifPresent(tasks -> tasks.stream().forEach(Task::run));
    }

    public synchronized void stopTimer(String group, String key) {
        Optional<Task> optional = findTask(group, key);
        if (optional.isPresent()) {
            Task task = optional.get();
            task.stop();
            timers.remove(task);
        }
    }

    public synchronized void stopGroupTimer(String group) {
        findGroupTask(group).ifPresent(tasks -> {
            tasks.stream().forEach(Task::stop);
            timers.removeAll(tasks);
        });
    }

    public synchronized void stopAllTimer() {
        findAllTask().ifPresent(tasks -> {
            tasks.stream().forEach(Task::stop);
            timers.removeAll(tasks);
        });
    }

    private Optional<List<Task>> findGroupTask(String group) {
        List<Task> tasks = timers.stream()
                .filter(a -> a.getGroup().equals(group))
                .collect(Collectors.toList());
        return Optional.of(tasks);
    }

    private Optional<List<Task>> findAllTask() {
        List<Task> tasks = timers.stream()
                .collect(Collectors.toList());
        return Optional.of(tasks);
    }

    private Optional<Task> findTask(String group, String key) {
        Optional<Task> optional = timers.stream().
                filter(a -> a.getGroup().equals(group)
                        && a.getKey().equals(key))
                .findFirst();
        return optional;
    }


    private static class Task {
        private String group;
        private String key;
        private TimerTask timerTask;
        private long delay;
        private long period;
        private boolean running;
        private Timer timer = new Timer();

        protected Task(String group, String key, TimerTask task, long delay, long period) {
            this.group = group;
            this.key = key;
            this.timerTask = task;
            this.delay = delay;
            this.period = period;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public TimerTask getTimerTask() {
            return timerTask;
        }

        public void setTimerTask(TimerTask timerTask) {
            this.timerTask = timerTask;
        }

        public long getDelay() {
            return delay;
        }

        public void setDelay(long delay) {
            this.delay = delay;
        }

        public long getPeriod() {
            return period;
        }

        public void setPeriod(long period) {
            this.period = period;
        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        public Timer getTimer() {
            return timer;
        }

        public void setTimer(Timer timer) {
            this.timer = timer;
        }

        public synchronized void run() {
            if (isRunning()) return;
            timer.schedule(timerTask, delay, period);
            setRunning(true);
        }

        public synchronized void stop() {
            if (!isRunning()) return;
            timer.cancel();
            setRunning(false);
        }
    }

}