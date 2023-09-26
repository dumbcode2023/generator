package com.greedystar.generator.task;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
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
 * @since 2018/4/20
 */
public class ControllerTask extends AbstractTask {

    public ControllerTask(DomainContext invoker) {
        this.domainCtx = invoker;
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 构造Controller填充数据
        Map<String, Object> controllerData = new HashMap<>();
        ProjectContext projectContext = domainCtx.getProjectCtx();
        controllerData.put("Configuration", projectContext);
        BeanContext serviceConfig = BeanContext.getConfig(BeanContext.Type.SERVICE);
        String serviceClassName =serviceConfig.getFormat().replace(Constant.PLACEHOLDER, domainCtx.getClassName());
        String serviceImport = String.format("import %s.%s.%s;", projectContext.getPackageName(),
                serviceConfig.getPath(), serviceClassName);
        controllerData.put("ServiceImport", serviceImport);
        controllerData.put("ServiceClassName", serviceClassName);
        controllerData.put("ServiceEntityName", StringUtil.firstToLowerCase(serviceClassName));
        BeanContext controllerConfig = BeanContext.getConfig(BeanContext.Type.CONTROLLER);
        controllerData.put("ControllerClassName", controllerConfig.getFormat()
                .replace(Constant.PLACEHOLDER, domainCtx.getClassName()));
        controllerData.put("ClassName", BeanContext.getConfig(BeanContext.Type.ENTITY).getFormat()
                .replace(Constant.PLACEHOLDER, domainCtx.getClassName()));
//        controllerData.put("pkType", getPrimaryKeyType(invoker.getColumnInfoList()));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectContext.getPackageName()) +
                StringUtil.package2Path(controllerConfig.getPath());
        String fileName = controllerConfig.getFormat().replace(Constant.PLACEHOLDER, domainCtx.getClassName()) + ".java";
        // 生成Controller文件
        FileUtil.generateToJava(FreemarkerConfigUtil.TYPE_CONTROLLER, controllerData, filePath, fileName);
    }

//    /**
//     * 获取主键列对应的属性类型
//     *
//     * @param columnInfos
//     * @return
//     */
//    private String getPrimaryKeyType(List<ColumnInfo> columnInfos) {
//        if (pro) {
//            return "Serializable";
//        }
//        for (ColumnInfo info : columnInfos) {
//            if (info.isPrimaryKey()) {
//                return info.getPropertyType();
//            }
//        }
//        return "Serializable";
//    }

}
