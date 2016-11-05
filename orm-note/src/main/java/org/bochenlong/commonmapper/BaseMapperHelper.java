package org.bochenlong.commonmapper;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

/**
 * Created by bochenlong on 16-11-2.
 */
public class BaseMapperHelper {
    private static final XMLLanguageDriver driver = new XMLLanguageDriver();

    public static void setSqlSource(MappedStatement ms) {
        String xmlSql = new BaseInsertMapperProvider().insertSelective(ms);
        SqlSource sqlSource = driver.createSqlSource(ms.getConfiguration(), "<script>\n\t" + xmlSql + "</script>", null);
        MetaObject msObject = SystemMetaObject.forObject(ms);
        msObject.setValue("sqlSource", sqlSource);
    }

}
