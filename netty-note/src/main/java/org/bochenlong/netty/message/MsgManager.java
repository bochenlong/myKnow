package org.bochenlong.netty.message;

import org.bochenlong.netty.message.bean.NettyMsg;
import org.bochenlong.netty.message.queue.MsgQueue;
import org.bochenlong.netty.message.queue.UserDefineOption;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgManager {
    
    private static MsgQueueType msgQueueType;
    
    public static MsgQueueType getMsgQueueType() {
        if (msgQueueType == null) setDefault();
        return msgQueueType;
    }
    
    public static void setDefault() {
        msgQueueType = MsgQueueType.DEFAULT;
    }
    
    public static void setUserDefine(MsgQueue<Short, NettyMsg> msgQueue) {
        UserDefineOption.me().setMsgQueue(msgQueue);
        msgQueueType = MsgQueueType.USER_DEFINE;
    }
    
    public enum MsgQueueType {
        DEFAULT,
        USER_DEFINE
    }
}
