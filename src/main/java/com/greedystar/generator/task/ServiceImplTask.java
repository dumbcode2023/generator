package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.describer.RField;
import com.greedystar.generator.task.base.AbstractClassTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.StringUtil;
import com.greedystar.generator.utils.TemplateUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class ServiceImplTask extends AbstractClassTask {

    private BeanContext parentInterf;

    private BeanContext daoContext;

    private BeanContext entity;

    public ServiceImplTask(BeanContext beanContext,
                           BeanContext parentInterface,
                           BeanContext daoContext,
                           BeanContext entity) {
        super(beanContext);
        this.parentInterf = parentInterface;
        this.daoContext = daoContext;
        this.entity = entity;
    }


    @Override
    public void run() throws IOException, TemplateException {
        // 构造Service填充数据
        Map<String, Object> data = new HashMap<>();
        DomainContext domainContext = beanContext.getDomainContext();
        ProjectContext projectCtx = domainContext.getProjectCtx();
        data.put("Configuration", projectCtx);

        //实现接口
        data.put("Implements", "implements " + parentInterf.formatClassName());
        //引入接口包
        data.put("InterfaceImport", "import " + projectCtx.getPackageName() + "." + parentInterf.getPath()
                + "." + parentInterf.formatClassName() + ";");

        classInfo = beanContext.toClass();
        classInfo.setFields(RField.ofBean(daoContext));
        data.put("classInfo", classInfo);

        data.put("dao", daoContext.toClass());
        data.put("entity", entity.toClass());
        data.put("parentInterface", parentInterf.toClass());
        String fileName = beanContext.formatClassName() + ".java";
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectCtx.getPackageName())
                + StringUtil.package2Path(beanContext.getPath());
        TemplateUtil.render(data, TemplateUtil.getInstance().getTemplate(template()), new File(filePath, fileName));
    }

    @Override
    protected String template() {
        return "Service.ftl";
    }
}