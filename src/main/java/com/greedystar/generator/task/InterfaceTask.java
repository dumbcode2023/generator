package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.describer.ClassDescriber;
import com.greedystar.generator.entity.Constant;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarkerConfigUtil;
import com.greedystar.generator.utils.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2019/1/24
 */
public class InterfaceTask extends AbstractTask {

    public InterfaceTask(DomainContext invoker) {
        this.domainCtx = invoker;
        buildDescriber(invoker);
    }
    private void buildDescriber(DomainContext invoker) {
        describer = new ClassDescriber();
        describer.setClassName(BeanContext.getConfig(BeanContext.Type.SERVICE).getFormat()
                .replace(Constant.PLACEHOLDER, invoker.getClassName()));
        describer.setComment(invoker.getComment());
    }
    @Override
    public void run() throws IOException, TemplateException {
        // 构造Service接口填充数据
        Map<String, Object> interfaceData = new HashMap<>();
        ProjectContext projectContext = domainCtx.getProjectCtx();
        interfaceData.put("Configuration", projectContext);
        interfaceData.put("objectInfo", describer);
        interfaceData.put("EntityName", StringUtil.firstToLowerCase(domainCtx.getClassName()));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectContext.getPackageName())
                + StringUtil.package2Path(BeanContext.getConfig(BeanContext.Type.SERVICE).getPath());
        String fileName = describer.getClassName() + ".java";
        // 生成Service接口文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_INTERFACE, interfaceData, filePath, fileName);
    }
}
