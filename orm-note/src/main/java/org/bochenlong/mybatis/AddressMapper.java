package org.bochenlong.mybatis;

import org.bochenlong.mybatis.bean.Address;

/**
 * Created by bochenlong on 16-11-8.
 */
public interface AddressMapper extends IMybatis {
    void insertSelective(Address address);
}
