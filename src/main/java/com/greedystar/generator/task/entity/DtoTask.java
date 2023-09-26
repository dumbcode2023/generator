package com.greedystar.generator.task.entity;


import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.task.base.AbstractClassTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarkerConfigUtil;
import com.greedystar.generator.utils.StringUtil;
import com.greedystar.generator.utils.TemplateUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DtoTask extends AbstractClassTask {

    private BeanContext beanContext;

    public DtoTask(BeanContext beanContext) {
        this.beanContext = beanContext;
    }

    @Override
    public void run() {
        // 构造Entity填充数据
        Map<String, Object> entityData = new HashMap<>();
        DomainContext domainContext = beanContext.getDomainContext();
        ProjectContext projectContext = domainContext.getProjectCtx();

        entityData.put("Configuration", domainContext.getProjectCtx());
        entityData.put("Model", describer);
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectContext.getPackageName())
                + StringUtil.package2Path(beanContext.getPath());
        String fileName = describer.getClassName() + ".java";
        try {
            TemplateUtil.render(entityData, FreemarkerConfigUtil.getInstance().getTemplate(getTemplate()), new File(filePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEvent(Integer eventType, Object data) {

    }

    @Override
    protected String getTemplate() {
        return null;
    }
}
