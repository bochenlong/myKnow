package org.bochenlong.bean;

import java.sql.Timestamp;

/**
 * Created by bochenlong on 16-9-22.
 */
public class User {
    private String name;
    private String password;
    private Timestamp createTime;

    public User() {
    }

    public User(String name, String password, Timestamp createTime) {
        this.name = name;
        this.password = password;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
