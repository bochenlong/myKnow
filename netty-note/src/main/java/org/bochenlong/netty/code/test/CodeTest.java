package org.bochenlong.netty.code.test;

import org.bochenlong.netty.code.impl.ByteCode;

/**
 * Created by bcl on 2016/8/29.
 */
public class CodeTest {
    public static void main(String[] args) {
        UserInfoDemo u = new UserInfoDemo().buildUserId(1).buildUserName("bochenlong");
        byte[] bytes = ByteCode.code(u);
        System.out.println(ByteCode.decode(bytes));
    }
}
