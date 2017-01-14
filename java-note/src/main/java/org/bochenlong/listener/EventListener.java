package org.bochenlong.listener;

import org.bochenlong.disruptor.DisruptorQueue;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bochenlong on 17-1-14.
 */
public class EventListener {
    private ConcurrentHashMap<Event, List<EventRespond>> ep =
            new ConcurrentHashMap<>();
    
    private ConcurrentHashMap<Event, DisruptorQueue> ed =
            new ConcurrentHashMap<>();
    
    private ExecutorService single = Executors.newSingleThreadExecutor();
    
    public void addListener(Event event, EventRespond respond) {
        
    }
}
