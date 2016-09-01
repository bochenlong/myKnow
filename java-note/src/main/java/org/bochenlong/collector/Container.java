package org.bochenlong.collector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bcl on 2016/8/25.
 */
public class Container {
    private List<Dish> ok = new ArrayList<>();
    private List<Dish> no = new ArrayList<>();

    public Container() {
    }

    public void addOk(Dish value) {
        ok.add(value);
    }

    public void addNo(Dish value) {
        no.add(value);
    }

    public List<Dish> getOk() {
        return ok;
    }

    public List<Dish> getNo() {
        return no;
    }
}
