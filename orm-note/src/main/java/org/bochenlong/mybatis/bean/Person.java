package org.bochenlong.mybatis.bean;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bochenlong on 16-11-8.
 */
public class Person {
    private Integer id;
    private String realName;
    private Character sex;

    @Transient
    private List<Address> addresses = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", addresses=" + addresses +
                '}';
    }
}
