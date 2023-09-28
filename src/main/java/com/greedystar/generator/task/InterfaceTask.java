package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.task.base.AbstractClassTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.TemplateUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.greedystar.generator.utils.StringUtil.firstToLowerCase;
import static com.greedystar.generator.utils.StringUtil.package2Path;

/**
 * @author GreedyStar
 * @since 2019/1/24
 */
public class InterfaceTask extends AbstractClassTask {

    private BeanContext entity;
    public InterfaceTask(BeanContext beanContext,BeanContext entity) {
        super(beanContext);
        this.entity = entity;
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Service接口填充数据
        Map<String, Object> interfaceData = new HashMap<>();
        DomainContext domainContext = beanContext.getDomainContext();
        ProjectContext projectContext = domainContext.getProjectCtx();
        interfaceData.put("Configuration", projectContext);
        classInfo = beanContext.toClass();
        interfaceData.put("classInfo", classInfo);
        interfaceData.put("entity",entity.toClass());
        interfaceData.put("EntityName", firstToLowerCase(domainContext.getClassName()));
        String filePath = FileUtil.getSourcePath() + package2Path(projectContext.getPackageName())
                + package2Path(beanContext.getPath());
        String fileName = classInfo.getName() + ".java";
        TemplateUtil.render(interfaceData, TemplateUtil.getInstance().getTemplate(template()), new File(filePath, fileName));
    }

    @Override
    protected String template() {
        return "Interface.ftl";
    }
}
