package org.bochenlong.netty.message;

import org.bochenlong.netty.message.bean.NettyMsg;
import org.bochenlong.netty.message.queue.realize.MsgQueue;
import org.bochenlong.netty.message.queue.DefaultQueueOption;
import org.bochenlong.netty.message.queue.UserDefineOption;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgHelper {
    private static Set<Short> set = new HashSet<>();
    
    public static boolean addMsgType(short type) {
        assert type >= 100;
        getMsgQueue().createQueue(type);
        return set.add(type);
    }
    
    public static boolean addMsgTypes(Set<Short> types) {
        types.stream().forEach(MsgHelper::addMsgType);
        return true;
    }
    
    public static boolean add(short type, NettyMsg message) {
        return getMsgQueue().add(type, message);
    }
    
    
    public static void take(short type, NettyMsg message) {
        getMsgQueue().take(type);
    }
    
    
    private static MsgQueue<Short, NettyMsg> getMsgQueue() {
        if (MsgManager.getMsgQueueType() == MsgManager.MsgQueueType.DEFAULT) {
            return DefaultQueueOption.me().getMsgQueue();
        } else if (MsgManager.getMsgQueueType() == MsgManager.MsgQueueType.USER_DEFINE) {
            return UserDefineOption.me().getMsgQueue();
        }
        return null;
    }
}
