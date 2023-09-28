package com.greedystar.generator.task.entity;


import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.TableInfo;
import com.greedystar.generator.invoker.SingleInvoker;
import com.greedystar.generator.utils.ConfigUtil;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class EntityTaskTest {

    @Test
    public void run() throws TemplateException, IOException {
        SingleInvoker abstractInvoker = Mockito.mock(SingleInvoker.class);
        DomainContext domainContext = new DomainContext(ConfigUtil.getConfiguration());
        domainContext.setClassName("Student");
        domainContext.setTableName("test_student");
        List<ColumnInfo> list = new ArrayList<>();
        ColumnInfo id = new ColumnInfo("id", JDBCType.BIGINT.getVendorTypeNumber()
                , "主键",  true);
        ColumnInfo age = new ColumnInfo("age", JDBCType.INTEGER.getVendorTypeNumber()
                , "年龄",  false);
        ColumnInfo name = new ColumnInfo("name", JDBCType.VARCHAR.getVendorTypeNumber()
                , "姓名",  false);
        list.add(id);
        list.add(age);
        list.add(name);
        TableInfo tableInfo = new TableInfo("学生表",list);
        domainContext.setTableInfo(tableInfo);
        when(abstractInvoker.getDomainContext()).thenReturn(domainContext);
        BeanContext beanContext = BeanContext.getConfig(BeanContext.Type.ENTITY);
        beanContext.setDomainContext(domainContext);
        EntityTask entityTask = new EntityTask(beanContext);
        entityTask.run();
    }

    public static DomainContext getDomainContext() {
        DomainContext domainContext = new DomainContext(ConfigUtil.getConfiguration());
        domainContext.setClassName("Student");
        domainContext.setTableName("test_student");
        List<ColumnInfo> list = new ArrayList<>();
        ColumnInfo id = new ColumnInfo("id", JDBCType.BIGINT.getVendorTypeNumber()
                , "主键" , true);
        id.setNullable(false);
        ColumnInfo age = new ColumnInfo("age", JDBCType.INTEGER.getVendorTypeNumber()
                , "年龄",  false);
        age.setNullable(true);
        ColumnInfo name = new ColumnInfo("name", JDBCType.VARCHAR.getVendorTypeNumber()
                , "姓名",  false);
        list.add(id);
        list.add(age);
        list.add(name);
        TableInfo tableInfo = new TableInfo("学生表",list);
        domainContext.setTableInfo(tableInfo);
        return domainContext;
    }
}