package org.bochenlong.codec;

import java.util.List;
import java.util.Map;

/**
 * Created by bochenlong on 16-9-20.
 */
public class Demo {
    private int id;
    private String name;
    private String address;
    private List<String> phones;
    private Map<String,String> tags;

    public Demo() {
    }

    public Demo(int id, String name, String address, List<String> phones, Map<String, String> tags) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.tags = tags;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
