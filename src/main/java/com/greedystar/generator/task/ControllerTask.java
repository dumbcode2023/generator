package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.describer.RField;
import com.greedystar.generator.task.base.AbstractClassTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.TemplateUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.greedystar.generator.entity.Constant.PLACEHOLDER;
import static com.greedystar.generator.utils.StringUtil.package2Path;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class ControllerTask extends AbstractClassTask {

    private final BeanContext serviceCtx;

    private final BeanContext entityCtx;

    public ControllerTask(BeanContext beanContext, BeanContext serviceCtx, BeanContext entityCtx) {
        super(beanContext);
        this.serviceCtx = serviceCtx;
        this.entityCtx = entityCtx;
    }

    @Override
    public void run() throws IOException, TemplateException {
        Map<String, Object> data = new HashMap<>();
        DomainContext domainCtx = beanContext.getDomainContext();
        ProjectContext projectCtx = domainCtx.getProjectCtx();
        data.put("Configuration", projectCtx);
        data.put("tableInfo", domainCtx.getTableInfo());
        data.put("imports", buildImports(domainCtx));
        classInfo = beanContext.toClass();
        classInfo.setFields(RField.ofBean(serviceCtx));
        data.put("classInfo", classInfo);
        data.put("service", serviceCtx.toClass());
        data.put("entity", entityCtx.toClass());
        String filePath = FileUtil.getSourcePath() + package2Path(projectCtx.getPackageName() +"."+
                beanContext.getPath());
        String fileName = beanContext.getFormat().replace(PLACEHOLDER, domainCtx.getClassName()) + ".java";
        TemplateUtil.render(data, TemplateUtil.getInstance().getTemplate(template()), new File(filePath, fileName));
    }

    private List<String> buildImports(DomainContext domainCtx) {
        String serviceImport = String.format("import %s.%s.%s;", domainCtx.getProjectCtx().getPackageName(),
                serviceCtx.getPath(), serviceCtx.formatClassName());
        List<String> list = new ArrayList<>();
        list.add(serviceImport);
        return list;
    }

    @Override
    protected String template() {
        return "Controller.ftl";
    }


}
