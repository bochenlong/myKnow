package org.bochenlong.pdxnetty.config;

import org.bochenlong.pdxnetty.NettyHelper;

/**
 * Created by bochenlong on 16-10-24.
 */
public class NettyConfig {
    public static String DEFAULT_HOST = NettyHelper.getLocalIP();
    public static int DEFAULT_PORT = 8989;

    public static int MSG_MAX_LEN = 1024 * 1024;
    public static int MSG_LEN_OFFSET = 0;
    public static int MSG_LEN_FIELD = 4;

    public static int BACKLOG_SIZE = 1204;

    public static int CONNECT_TIME_OUT = 1000 * 30;

    public static int SEND_TIME_OUT = 1000 * 15;


    public static String getDefaultHost() {
        return DEFAULT_HOST;
    }

    public static void setDefaultHost(String defaultHost) {
        DEFAULT_HOST = defaultHost;
    }

    public static int getDefaultPort() {
        return DEFAULT_PORT;
    }

    public static void setDefaultPort(int defaultPort) {
        DEFAULT_PORT = defaultPort;
    }

    public static int getMsgMaxLen() {
        return MSG_MAX_LEN;
    }

    public static void setMsgMaxLen(int msgMaxLen) {
        MSG_MAX_LEN = msgMaxLen;
    }

    public static int getMsgLenOffset() {
        return MSG_LEN_OFFSET;
    }

    public static void setMsgLenOffset(int msgLenOffset) {
        MSG_LEN_OFFSET = msgLenOffset;
    }

    public static int getMsgLenField() {
        return MSG_LEN_FIELD;
    }

    public static void setMsgLenField(int msgLenField) {
        MSG_LEN_FIELD = msgLenField;
    }

    public static int getBacklogSize() {
        return BACKLOG_SIZE;
    }

    public static void setBacklogSize(int backlogSize) {
        BACKLOG_SIZE = backlogSize;
    }

    public static int getConnectTimeOut() {
        return CONNECT_TIME_OUT;
    }

    public static void setConnectTimeOut(int connectTimeOut) {
        CONNECT_TIME_OUT = connectTimeOut;
    }

    public static int getSendTimeOut() {
        return SEND_TIME_OUT;
    }

    public static void setSendTimeOut(int sendTimeOut) {
        SEND_TIME_OUT = sendTimeOut;
    }


}
