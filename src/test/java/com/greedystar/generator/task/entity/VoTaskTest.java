//package com.greedystar.generator.task.entity;
//
//import com.greedystar.generator.context.BeanContext;
//import com.greedystar.generator.context.DomainContext;
//import org.junit.Test;
//
//import static com.greedystar.generator.task.entity.DtoTaskTest.getDomainContext;
//
//public class VoTaskTest {
//
//    @Test
//    public void run() {
//        DomainContext domainContext = getDomainContext();
//        BeanContext beanContext = BeanContext.getConfig(BeanContext.Type.ENTITY_VO);
//        beanContext.setDomainContext(domainContext);
//        DoTask dtoTask = new DoTask(beanContext);
//        dtoTask.run();
//    }
//
//    @Test
//    public void fields() {
//    }
//
//    @Test
//    public void template() {
//    }
//}