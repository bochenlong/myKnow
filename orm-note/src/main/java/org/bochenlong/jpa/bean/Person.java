package org.bochenlong.jpa.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bochenlong on 16-10-26.
 */
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "person_id")
    private Integer id;

    private String fullname;

    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
