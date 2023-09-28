package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
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
public class DaoTask extends AbstractClassTask {

    private BeanContext entity;

    public DaoTask(BeanContext beanContext, BeanContext entity) {
        super(beanContext);
        this.entity = entity;
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Dao填充数据
        Map<String, Object> daoData = new HashMap<>();
        DomainContext domainContext = beanContext.getDomainContext();
        ProjectContext projectContext = domainContext.getProjectCtx();
        daoData.put("Configuration", projectContext);
        classInfo = beanContext.toClass();
        daoData.put("classInfo", classInfo);
        daoData.put("ClassName", domainContext.getClassName());
        daoData.put("entity", entity.toClass());

        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectContext.getPackageName())
                + StringUtil.package2Path(beanContext.getPath());
        String fileName = classInfo.getName() + ".java";
        TemplateUtil.render(daoData, TemplateUtil.getInstance().getTemplate(template()), new File(filePath, fileName));
    }

    @Override
    protected String template() {
        return "Dao.ftl";
    }


}
