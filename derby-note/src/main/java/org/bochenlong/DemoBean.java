package org.bochenlong;

import java.sql.Timestamp;

/**
 * Created by bochenlong on 2016/9/19.
 */
public class DemoBean {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Timestamp createTime;

    public DemoBean() {
    }

    public DemoBean(int id, String name, String email, String phone, Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
