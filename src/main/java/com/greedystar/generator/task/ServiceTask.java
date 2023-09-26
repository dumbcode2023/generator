package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.describer.ClassDescriber;
import com.greedystar.generator.describer.FieldDescriber;
import com.greedystar.generator.task.base.AbstractJavaTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarkerConfigUtil;
import com.greedystar.generator.utils.StringUtil;
import com.greedystar.generator.utils.TemplateUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.greedystar.generator.entity.Constant.PLACEHOLDER;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class ServiceTask extends AbstractJavaTask {

    public ServiceTask(DomainContext domainContext) {
        this.domainCtx = domainContext;
        buildDescriber(this.domainCtx);
    }

    private void buildDescriber(DomainContext domainContext) {
        describer = new ClassDescriber();
        String serviceClassName = BeanContext.getConfig(BeanContext.Type.SERVICE_IMPL).getFormat()
                .replace(PLACEHOLDER, domainContext.getClassName());
        serviceClassName = serviceClassName.contains("Impl") ? serviceClassName : serviceClassName + "Impl";
        describer.setClassName(serviceClassName);
        describer.setComment(domainContext.getColumnInfoList().get(0).getTableRemarks());
        describer.setFieldList(buildFields());
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Service填充数据
        Map<String, Object> data = new HashMap<>();
        ProjectContext projectCtx = domainCtx.getProjectCtx();
        data.put("Configuration", projectCtx);
        BeanContext beanCtx = BeanContext.getConfig(BeanContext.Type.SERVICE_IMPL);
        String className = BeanContext.getConfig(BeanContext.Type.SERVICE_IMPL).getFormat()
                .replace(PLACEHOLDER, domainCtx.getClassName());
        //实现接口
        data.put("Implements", "implements " + className.replace(PLACEHOLDER, domainCtx.getClassName()));
        BeanContext parentInterface = BeanContext.getConfig(BeanContext.Type.SERVICE);
        //引入接口包
        data.put("InterfaceImport", "import " + projectCtx.getPackageName() + "."
                + parentInterface.getPath() + "."
                + parentInterface.getFormat().replace(PLACEHOLDER, domainCtx.getClassName()) + ";");

        data.put("objectInfo", describer);

        String fileName = className + ".java";
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectCtx.getPackageName())
                + StringUtil.package2Path(beanCtx.getPath());

        TemplateUtil.render(data, FreemarkerConfigUtil.getInstance().getTemplate(getTemplate()), new File(filePath, fileName));
    }

    private List<FieldDescriber> buildFields() {
        List<FieldDescriber> fieldList = new ArrayList<>();
        FieldDescriber field = new FieldDescriber();
        BeanContext daoConfig = BeanContext.getConfig(BeanContext.Type.DAO);
        String daoClassName = daoConfig.getFormat().replace(PLACEHOLDER, domainCtx.getClassName());
        field.setName(StringUtil.firstToLowerCase(daoClassName));
        field.setModifier("private");
        ClassDescriber daoClass = new ClassDescriber();
        daoClass.setClassName(daoClassName);
        daoClass.setPackageName(daoConfig.getPath());
        field.setClassType(daoClass);
        fieldList.add(field);
        return fieldList;
    }

    @Override
    protected String getTemplate() {
        return "Service.ftl";
    }
}