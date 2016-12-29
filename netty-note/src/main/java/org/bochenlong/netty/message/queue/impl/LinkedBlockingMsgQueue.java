package org.bochenlong.netty.message.queue.impl;

import org.bochenlong.netty.message.bean.NettyMsg;
import org.bochenlong.netty.message.queue.MsgQueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bochenlong on 16-12-27.
 */
public class LinkedBlockingMsgQueue implements MsgQueue<Short, NettyMsg> {
    private ConcurrentHashMap<Short, LinkedBlockingQueue<NettyMsg>> queueMap =
            new ConcurrentHashMap<>();
    
    @Override
    public void createQueue(Short type) {
        queueMap.computeIfAbsent(type, a -> new LinkedBlockingQueue<>());
    }
    
    @Override
    public boolean add(Short type, NettyMsg nettyMsg) {
        return queueMap.get(type).add(nettyMsg);
    }
    
    @Override
    public NettyMsg take(Short type) {
        try {
            return queueMap.get(type).take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
