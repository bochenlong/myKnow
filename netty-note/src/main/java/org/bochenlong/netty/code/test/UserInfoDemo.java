package org.bochenlong.netty.code.test;

import java.io.Serializable;

/**
 * Created by bcl on 2016/8/29.
 */
public class UserInfoDemo implements Serializable {
    private String userName;
    private int userId;

    public UserInfoDemo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserInfoDemo buildUserId(int id) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
