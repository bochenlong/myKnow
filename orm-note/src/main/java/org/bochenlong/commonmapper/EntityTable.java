package org.bochenlong.commonmapper;

import java.util.LinkedHashMap;

/**
 * Created by bochenlong on 16-11-1.
 */
public class EntityTable {
    private String tableName;
    private LinkedHashMap<String, String> propertyColumn = new LinkedHashMap<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LinkedHashMap<String, String> getPropertyColumn() {
        return propertyColumn;
    }

    public void setPropertyColumn(LinkedHashMap<String, String> propertyColumn) {
        this.propertyColumn = propertyColumn;
    }


    public void addBeanColumn(String property, String column) {
        propertyColumn.put(property, column);
    }

}
