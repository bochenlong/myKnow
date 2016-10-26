package org.bochenlong.bean.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bochenlong on 16-10-26.
 */
public class User {
    private Integer id;
    private String name;
    private List<String> addresses = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(String address) {
        this.addresses.add(address);
    }
}
