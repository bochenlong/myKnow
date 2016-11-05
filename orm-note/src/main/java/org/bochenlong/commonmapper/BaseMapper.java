package org.bochenlong.commonmapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bochenlong on 16-10-29.
 */
public interface BaseMapper<T> extends BaseInsertMapper<T> {
    Set<String> baseMethod = new HashSet() {{
        add("insert");
        add("insertSelective");
    }};
    String paramPre = "entity.";
}
