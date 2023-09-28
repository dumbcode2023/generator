package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;

import static com.greedystar.generator.task.entity.EntityTaskTest.getDomainContext;

public class ServiceImplTaskTest {

    @Test
    public void run() throws TemplateException, IOException {
        DomainContext domainContext = getDomainContext();
        BeanContext beanContext = BeanContext.getConfig(BeanContext.Type.SERVICE_IMPL);
        beanContext.setDomainContext(domainContext);

        BeanContext interf = BeanContext.getConfig(BeanContext.Type.INTERF);
        interf.setDomainContext(domainContext);

        BeanContext dao = BeanContext.getConfig(BeanContext.Type.DAO);
        dao.setDomainContext(domainContext);

        BeanContext entity = BeanContext.getConfig(BeanContext.Type.ENTITY);
        entity.setDomainContext(domainContext);

        ServiceImplTask task = new ServiceImplTask(beanContext,interf,dao,entity);
        task.run();
    }
}