package org.bochenlong.listener;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bochenlong on 17-1-14.
 */
public class EventListener {
    private ConcurrentHashMap<Event, List<EventRespond>> ep =
            new ConcurrentHashMap<>();
    
    public void addListener(Event event, EventRespond respond) {
        //reactor
    }
}
