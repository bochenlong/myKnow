package org.bochenlong.netty.message.queue;

import org.bochenlong.netty.message.bean.NettyMsg;
import org.bochenlong.netty.message.queue.realize.MsgQueue;

/**
 * Created by bochenlong on 16-12-29.
 */
public class UserDefineOption {
    private static class Holder {
        private static UserDefineOption queueOption = new UserDefineOption();
    }
    
    public static UserDefineOption me() {
        return UserDefineOption.Holder.queueOption;
    }
    
    private MsgQueue<Short, NettyMsg> msgQueue = null;
    
    
    public MsgQueue<Short, NettyMsg> getMsgQueue() {
        return me().msgQueue;
    }
    
    public void setMsgQueue(MsgQueue<Short, NettyMsg> msgQueue) {
        me().msgQueue = msgQueue;
    }
}
