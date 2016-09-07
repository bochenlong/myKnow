package org.bochenlong;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bochenlong.netty.codec.ICodec;
import org.bochenlong.netty.codec.ProtostuffCodec;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bcl on 2016/8/30.
 */
public class ICodecTest {
    public static void main(String[] args) throws Exception {
        UserInfo u = new UserInfo().setEmail("317144004@qq.com").setName("bochenlong").setPassword("222222")
                .setPhone("18518757071");

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(u.getName().getBytes());
        byteBuf.writeBytes(u.getPassword().getBytes());
        byteBuf.writeBytes(u.getEmail().getBytes());
        byteBuf.writeBytes(u.getPassword().getBytes());
        byteBuf.writeInt(4);
        for (Map.Entry<String, Integer> param : u.getM().entrySet()) {
            byteBuf.writeBytes(param.getKey().getBytes());
            byteBuf.writeInt(param.getValue());
        }

        System.out.println(byteBuf.readableBytes());
        System.out.println(new ProtostuffCodec<UserInfo>().toBytes(u).length);

        byteBuf = Unpooled.buffer();
        String a = "avccd";
        String b = "ssed";
        byte[] bytes = a.getBytes();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        bytes = b.getBytes();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        int al = byteBuf.readInt();
        byte[] bytes1 = new byte[al];
        byteBuf.readBytes(bytes1);
        System.out.println(al + new String(bytes1));
        int bl = byteBuf.readInt();
        bytes1 = new byte[bl];
        byteBuf.readBytes(bytes1);
        System.out.println(bl + new String(bytes1));
        System.out.println(Charset.defaultCharset().name());
    }

    public static class UserInfo implements Serializable {

        @Override
        public String toString() {
            return name;
        }

        private String name;
        private String password;
        private String email;
        private String phone;

        private Map<String, Integer> m = new HashMap<String, Integer>() {
            {
                put("bochenlong", 1);
                put("bochenlong2", 2);
                put("bochenlong3", 3);
                put("bochenlong4", 4);
            }

        };

        public Map<String, Integer> getM() {
            return m;
        }

        public void setM(Map<String, Integer> m) {
            this.m = m;
        }

        public String getName() {
            return name;
        }

        public UserInfo setName(String name) {
            this.name = name;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public UserInfo setPassword(String password) {
            this.password = password;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public UserInfo setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public UserInfo setPhone(String phone) {
            this.phone = phone;
            return this;
        }
    }
}
