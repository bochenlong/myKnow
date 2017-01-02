package org.bochenlong.netty.message.queue;

import org.bochenlong.netty.message.bean.NettyMsg;
import org.bochenlong.netty.message.queue.realize.DefaultMsgQueue;
import org.bochenlong.netty.message.queue.realize.MsgQueue;

/**
 * Created by bochenlong on 16-12-22.
 */
public class DefaultQueueOption {
    private static class Holder {
        private static DefaultQueueOption queueOption = new DefaultQueueOption();
    }
    
    public static DefaultQueueOption me() {
        return Holder.queueOption;
    }
    
    private MsgQueue<Short, NettyMsg> msgQueue =
            new DefaultMsgQueue();
    
    
    public MsgQueue<Short, NettyMsg> getMsgQueue() {
        return me().msgQueue;
    }
    
    public void setMsgQueue(MsgQueue<Short, NettyMsg> msgQueue) {
        me().msgQueue = msgQueue;
    }
}
