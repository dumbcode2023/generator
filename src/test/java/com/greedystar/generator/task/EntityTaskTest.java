package com.greedystar.generator.task;


import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.entity.Mode;
import com.greedystar.generator.invoker.SingleInvoker;
import com.greedystar.generator.task.entity.EntityTask;
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
        DomainContext domainContext = new DomainContext();
        domainContext.setClassName("Student");
        domainContext.setTableName("test_student");
        when(abstractInvoker.getDomainContext()).thenReturn(domainContext);

        List<ColumnInfo> list = new ArrayList<>();
        ColumnInfo id = new ColumnInfo("id", JDBCType.BIGINT.getVendorTypeNumber()
                , "主键", "学生表", true);
        ColumnInfo age = new ColumnInfo("age", JDBCType.INTEGER.getVendorTypeNumber()
                , "年龄", "学生表", false);
        ColumnInfo name = new ColumnInfo("name", JDBCType.VARCHAR.getVendorTypeNumber()
                , "姓名", "学生表", false);
        list.add(id);
        list.add(age);
        list.add(name);
        domainContext.setColumnInfoList(list);
        EntityTask entityTask = new EntityTask(Mode.ENTITY_MAIN, domainContext);
        entityTask.run();
    }


}