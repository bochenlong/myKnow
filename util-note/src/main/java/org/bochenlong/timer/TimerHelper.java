package org.bochenlong.timer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bochenlong on 16-11-22.
 */
public class TimerHelper {

    public static String DEFAULT_GROUP = "default";

    private static class holder {
        private static TimerHelper timerHelper = new TimerHelper();
    }

    private List<Task> timers;


    public static TimerHelper instance() {
        return holder.timerHelper;
    }

    private TimerHelper() {
        timers = new ArrayList<>();
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

    public synchronized void registerAndRun(String key, TimerTask timerTask,
                                            long delay, long period) {
        registerAndRun(DEFAULT_GROUP, key, timerTask, delay, period);
    }

    public synchronized void registerAndRun(String group, String key, TimerTask timerTask,
                                            long delay, long period) {
        Optional<Task> optional = findTask(group, key);

        if (optional.isPresent()) {
            Task task = optional.get();
            if (!task.isRunning()) task.run();
        } else {
            Task task = new Task(group, key, timerTask, delay, period);
            timers.add(task);
            task.run();
        }
    }

    public void startTimer(String key) {
        startTimer(DEFAULT_GROUP, key);
    }

    public void startTimer(String group, String key) {
        findTask(group, key).ifPresent(Task::run);
    }

    public void startGroupTimer(String group) {
        findGroupTask(group).ifPresent(a -> a.stream().forEach(Task::run));
    }

    public void startAllTimer() {
        findAllTask().ifPresent(tasks -> tasks.stream().forEach(Task::run));
    }

    public void stopTimer(String key) {
        stopTimer(DEFAULT_GROUP, key);
    }

    public void stopTimer(String group, String key) {
        findTask(group, key).ifPresent(a -> {
            a.stop();
            timers.remove(a);
        });
    }

    public void stopGroupTimer(String group) {
        findGroupTask(group).ifPresent(tasks -> {
            tasks.stream().forEach(Task::stop);
            timers.removeAll(tasks);
        });
    }

    public void stopAllTimer() {
        findAllTask().ifPresent(tasks -> {
            tasks.stream().forEach(Task::stop);
            timers.removeAll(tasks);
        });
    }

    private Optional<Task> findTask(String group, String key) {
        Optional<Task> optional = timers.stream().
                filter(a -> a.getGroup().equals(group)
                        && a.getKey().equals(key))
                .findFirst();
        return optional;
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