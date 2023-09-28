package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;

import static com.greedystar.generator.task.entity.EntityTaskTest.getDomainContext;


public class InterfaceTaskTest {

    @Test
    public void run() throws TemplateException, IOException {
        DomainContext domainContext = getDomainContext();
        BeanContext beanContext = BeanContext.getConfig(BeanContext.Type.INTERF);
        beanContext.setDomainContext(domainContext);

        InterfaceTask task = new InterfaceTask(beanContext);
        task.run();
    }
}