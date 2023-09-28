//package com.greedystar.generator.task.entity;
//
//import com.greedystar.generator.context.BeanContext;
//import com.greedystar.generator.context.DomainContext;
//import com.greedystar.generator.entity.ColumnInfo;
//import com.greedystar.generator.invoker.SingleInvoker;
//import com.greedystar.generator.utils.ConfigUtil;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import java.sql.JDBCType;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//public class DtoTaskTest {
//
//    @Test
//    public void run() {
//        DomainContext domainContext = getDomainContext();
//        BeanContext beanContext = BeanContext.getConfig(BeanContext.Type.ENTITY);
//        beanContext.setDomainContext(domainContext);
//        DtoTask dtoTask = new DtoTask(beanContext);
//        dtoTask.run();
//    }
//
//    public static DomainContext getDomainContext() {
//        DomainContext domainContext = new DomainContext(ConfigUtil.getConfiguration());
//        domainContext.setClassName("Student");
//        domainContext.setTableName("test_student");
//        List<ColumnInfo> list = new ArrayList<>();
//        ColumnInfo id = new ColumnInfo("id", JDBCType.BIGINT.getVendorTypeNumber()
//                , "主键", "学生表", true);
//        id.setNullable(false);
//        ColumnInfo age = new ColumnInfo("age", JDBCType.INTEGER.getVendorTypeNumber()
//                , "年龄", "学生表", false);
//        age.setNullable(true);
//        ColumnInfo name = new ColumnInfo("name", JDBCType.VARCHAR.getVendorTypeNumber()
//                , "姓名", "学生表", false);
//        list.add(id);
//        list.add(age);
//        list.add(name);
//        domainContext.setColumnInfoList(list);
//        return domainContext;
//    }
//}