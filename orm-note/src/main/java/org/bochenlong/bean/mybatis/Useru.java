package org.bochenlong.bean.mybatis;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bochenlong on 16-10-26.
 */
public class Useru {
    private Integer id;
    private String fullname;

    @Transient
    private List<Location> locations = new ArrayList<>();

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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocations(Location location) {
        this.locations.add(location);
    }
}
