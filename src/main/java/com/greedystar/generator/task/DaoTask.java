package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.describer.ClassDescriber;
import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.task.base.AbstractClassTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarkerConfigUtil;
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

    public DaoTask(DomainContext domainContext) {
        this.domainCtx = domainContext;
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Dao填充数据
        Map<String, Object> daoData = new HashMap<>();
        ProjectContext projectContext = domainCtx.getProjectCtx();
        daoData.put("Configuration", projectContext);
        describer = new ClassDescriber();
        BeanContext config = getBeanConfig();
        describer.setClassName(config.getFormat().replace(Constant.PLACEHOLDER, domainCtx.getClassName()));
        describer.setComment(domainCtx.getComment());
        daoData.put("classInfo", describer);
        daoData.put("EntityName", StringUtil.firstToLowerCase(domainCtx.getClassName()));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectContext.getPackageName())
                + StringUtil.package2Path(config.getPath());
        String fileName = describer.getClassName() + ".java";
        TemplateUtil.render(daoData,FreemarkerConfigUtil.getInstance().getTemplate(getTemplate()), new File(filePath, fileName));
    }

    @Override
    protected String getTemplate() {
        return "Dao.ftl";
    }


    protected BeanContext getBeanConfig() {
        return BeanContext.getConfig(BeanContext.Type.DAO);
    }

    @Override
    public void onEvent(Integer eventType, Object data) {

    }
}
