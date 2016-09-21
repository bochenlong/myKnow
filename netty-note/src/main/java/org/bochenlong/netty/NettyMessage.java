package org.bochenlong.netty;

/**
 * Created by bcl on 2016/8/30.
 */
public final class NettyMessage {
    private Header header;// 消息头
    private Invocation body;// 消息体

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    public NettyMessage() {
    }

    public NettyMessage(Header header, Invocation body) {
        this.header = header;
        this.body = body;
    }

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Invocation getBody() {
        return body;
    }

    public final void setBody(Invocation body) {
        this.body = body;
    }

}