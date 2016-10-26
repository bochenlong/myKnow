package org.bochenlong.bean.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bochenlong on 16-10-26.
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id", columnDefinition = "int")
    private Integer id;
    @Column(name = "name",columnDefinition = "varchar(100")
    private String name;

    @OneToMany(mappedBy = "address")
    private List<Address> addresses = new ArrayList<>();

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
