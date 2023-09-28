//package com.greedystar.generator.task.entity;
//
//
//import com.greedystar.generator.context.BeanContext;
//import com.greedystar.generator.context.DomainContext;
//import com.greedystar.generator.context.ProjectContext;
//import com.greedystar.generator.describer.RClass;
//import com.greedystar.generator.describer.RField;
//import com.greedystar.generator.entity.Dependency;
//import com.greedystar.generator.exceptions.BusinessException;
//import com.greedystar.generator.task.base.AbstractClassTask;
//import com.greedystar.generator.utils.FileUtil;
//import com.greedystar.generator.utils.StringUtil;
//import com.greedystar.generator.utils.TemplateUtil;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class VoTask extends AbstractClassTask {
//
//
//    public VoTask(BeanContext beanContext) {
//        this.beanContext = beanContext;
//    }
//
//    @Override
//    public void run() {
//        Map<String, Object> data = new HashMap<>();
//        DomainContext domainContext = beanContext.getDomainContext();
//        ProjectContext projectContext = domainContext.getProjectCtx();
//        data.put("Configuration", projectContext);
//        classInfo = new RClass(beanContext.getPath(),beanContext.formatClassName(), fields());
//        classInfo.setComment(domainContext.getComment());
//        data.put("classInfo", classInfo);
//
//        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectContext.getPackageName())
//                + StringUtil.package2Path(beanContext.getPath());
//        String fileName = classInfo.getName() + ".java";
//        try {
//            TemplateUtil.render(data, TemplateUtil.getInstance().getTemplate(template()), new File(filePath, fileName));
//        } catch (IOException e) {
//            throw new BusinessException(e);
//        }
//    }
//
//    public RField[] fields() {
//        DomainContext domainContext = beanContext.getDomainContext();
//        return domainContext.getColumnInfoList().stream().map(c -> {
//            RField field = RField.of(c.getPropertyName());
//            field.setComment(c.getRemarks());
//            field.setClassType(RClass.of(c.getPropertyType()));
//            ProjectContext projectCtx = domainContext.getProjectCtx();
//            if (projectCtx.getDependency(Dependency.ALIAS_KNIFE4J).isEnabled() ||
//                    projectCtx.getDependency(Dependency.ALIAS_SWAGGER).isEnabled()) {
//                field.getAnnotations().add(String.format("@ApiModelProperty(value = \"%s\",required = %s)", field.getComment(), !c.isNullable()));
//            }
//            return field;
//        }).collect(Collectors.toList()).toArray(new RField[]{});
//    }
//
//    @Override
//    protected String template() {
//        return "entities/Dto.ftl";
//    }
//}
