package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;

import static com.greedystar.generator.task.entity.EntityTaskTest.getDomainContext;


public class ControllerTaskTest {

    @Test
    public void run() throws TemplateException, IOException {
        DomainContext domainContext = getDomainContext();
        BeanContext beanContext = BeanContext.getConfig(BeanContext.Type.CONTROLLER);
        beanContext.setDomainContext(domainContext);

        BeanContext service = BeanContext.getConfig(BeanContext.Type.INTERF);
        service.setDomainContext(domainContext);

        BeanContext entity = BeanContext.getConfig(BeanContext.Type.ENTITY);
        entity.setDomainContext(domainContext);

        ControllerTask dtoTask = new ControllerTask(beanContext,service,entity);
        dtoTask.run();
    }
}