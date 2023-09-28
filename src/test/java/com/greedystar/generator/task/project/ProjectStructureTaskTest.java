package com.greedystar.generator.task.project;

import com.greedystar.generator.context.DomainContext;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;

import static com.greedystar.generator.task.entity.EntityTaskTest.getDomainContext;

public class ProjectStructureTaskTest {

    @Test
    public void run() throws TemplateException, IOException {
        DomainContext domainContext = getDomainContext();
        ProjectStructureTask dtoTask = new ProjectStructureTask(domainContext);
        dtoTask.run();
    }
}