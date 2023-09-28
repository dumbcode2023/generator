package com.greedystar.generator.task.entity;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.describer.RClass;
import com.greedystar.generator.describer.RField;
import com.greedystar.generator.entity.IdStrategy;
import com.greedystar.generator.entity.TableInfo;
import com.greedystar.generator.task.base.AbstractClassTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.ForEachUtil;
import com.greedystar.generator.utils.StringUtil;
import com.greedystar.generator.utils.TemplateUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GreedyStar
 * @since 2018/4/20
 */
public class EntityTask extends AbstractClassTask {

    private String templatePath = "Entity.ftl";

    public EntityTask(BeanContext beanContext) {
        super(beanContext);
    }

    @Override
    public void run() throws IOException, TemplateException {
        DomainContext domainContext = beanContext.getDomainContext();
        ProjectContext projectContext = domainContext.getProjectCtx();
        Map<String, Object> entityData = new HashMap<>();
        entityData.put("Configuration", projectContext);
        entityData.put("TableName", domainContext.getTableName());

        classInfo = beanContext.toClass();
        classInfo.setFields(fields());
        entityData.put("classInfo", classInfo);

        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(projectContext.getPackageName())
                + StringUtil.package2Path(beanContext.getPath());
        String fileName = classInfo.getName() + ".java";
        TemplateUtil.render(entityData, TemplateUtil.getInstance().getTemplate(template())
                , new File(filePath, fileName));
    }

    /**
     * 生成实体类属性字段
     *
     * @return 属性代码段
     */
    public RField[] fields() {
        List<RField> fields = new ArrayList<>();
        DomainContext domainContext = beanContext.getDomainContext();
        TableInfo tableInfo = domainContext.getTableInfo();
        tableInfo.getColumns().forEach(ForEachUtil.withIndex((info, index) -> {
            if (info.getName().equals(domainContext.getForeignKey())) {
                return;
            }
            RField field = RField.of(info.getPropertyName());
            field.setComment(info.getRemarks());
            field.setClassType(RClass.of(info.getPropertyType()));
            if (info.isPrimaryKey()) {
                ProjectContext projectContext = domainContext.getProjectCtx();
                if (projectContext.getIdStrategy().equals(IdStrategy.AUTO)) {
                    field.getAnnotations().add(String.format("@TableId(value = \"%s\", type = IdType.AUTO)", field.getName()));
                } else {
                    field.getAnnotations().add(String.format("@TableId(value = \"%s\")", field.getName()));
                }
            }
            fields.add(field);
        }));
        return fields.toArray(new RField[]{});
    }

    @Override
    protected String template() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }


    /**
     * 生成实体类存取方法
     *
     * @param invoker 执行器
     * @return 方法代码段
     */
//    public String entityMethods(AbstractInvoker invoker) {
//        if (invoker.getConfiguration().isLombokEnable()) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        columnInfoList.forEach(ForEachUtil.withIndex((info, index) -> {
//            if (info.getColumnName().equals(invoker.getForeignKey())) {
//                return;
//            }
//            String setter = String.format("public void set%s (%s %s) { this.%s = %s; } \n\n", StringUtil.firstToUpperCase(info.getPropertyName()),
//                    info.getPropertyType(), info.getPropertyName(), info.getPropertyName(), info.getPropertyName());
//            sb.append(index == 0 ? "" : Constant.SPACE_4).append(setter);
//            String getter;
//            if (info.getPropertyType().equals("boolean")) {
//                getter = String.format("public %s is%s () { return this.%s; } \n\n", info.getPropertyType(),
//                        StringUtil.firstToUpperCase(info.getPropertyName()), info.getPropertyName());
//            } else {
//                getter = String.format("public %s get%s () { return this.%s; } \n\n", info.getPropertyType(),
//                        StringUtil.firstToUpperCase(info.getPropertyName()), info.getPropertyName());
//            }
//            sb.append(Constant.SPACE_4).append(getter);
//        }));
//        // 生成父表实体类时，直接截断后续生成依赖关系的代码
//        if (Mode.ENTITY_PARENT.equals(mode)) {
//            return sb.toString();
//        }
//        if (!StringUtil.isEmpty(invoker.getRelationalTableName()) || !StringUtil.isEmpty(invoker.getParentForeignKey())) {
//            // 多对多
//            String setter = String.format("public void set%ss (List<%s> %ss) { this.%ss = %ss; }\n\n", invoker.getParentClassName(),
//                    invoker.getParentClassName(), StringUtil.firstToLowerCase(invoker.getParentClassName()),
//                    StringUtil.firstToLowerCase(invoker.getParentClassName()), StringUtil.firstToLowerCase(invoker.getParentClassName()));
//            sb.append(Constant.SPACE_4).append(setter);
//            String getter = String.format("public List<%s> get%ss () { return this.%ss; }\n\n", invoker.getParentClassName(),
//                    invoker.getParentClassName(), StringUtil.firstToLowerCase(invoker.getParentClassName()));
//            sb.append(Constant.SPACE_4).append(getter);
//        } else if (!StringUtil.isEmpty(invoker.getForeignKey())) {
//            // 多对一
//            String setter = String.format("public void set%s (%s %s) { this.%s = %s; }\n\n", invoker.getParentClassName(),
//                    invoker.getParentClassName(), StringUtil.firstToLowerCase(invoker.getParentClassName()),
//                    StringUtil.firstToLowerCase(invoker.getParentClassName()), StringUtil.firstToLowerCase(invoker.getParentClassName()));
//            sb.append(Constant.SPACE_4).append(setter);
//            String getter = String.format("public %s get%s () { return this.%s; }\n\n", invoker.getParentClassName(), invoker.getParentClassName(),
//                    StringUtil.firstToLowerCase(invoker.getParentClassName()));
//            sb.append(Constant.SPACE_4).append(getter);
//        }
//        return sb.toString();
//    }


    /**
     * 为实体属性生成Orm框架（jpa/mybatis-plus）注解
     *
     * @param sb   StringBuilder对象
     * @param info 列属性
     */
//    public void generateORMAnnotation(StringBuilder sb, ColumnInfo info) {
//        if (ConfigUtil.getConfiguration().isMybatisPlusEnable()) {
//            if (info.isPrimaryKey()) {
//                if (ConfigUtil.getConfiguration().getIdStrategy() == null || ConfigUtil.getConfiguration().getIdStrategy() == IdStrategy.AUTO) {
//                    sb.append(Constant.SPACE_4).append(String.format("@TableId(value = \"%s\", type = IdType.AUTO)\n", info.getColumnName()));
//                } else if (ConfigUtil.getConfiguration().getIdStrategy() == IdStrategy.UUID) {
//                    sb.append(Constant.SPACE_4).append(String.format("@TableId(value = \"%s\", type = IdType.ASSIGN_UUID)\n", info.getColumnName()));
//                }
//            } else {
//                sb.append(Constant.SPACE_4).append(String.format("@TableField(value = \"%s\")\n", info.getColumnName()));
//            }
//        } else if (ConfigUtil.getConfiguration().isJpaEnable()) {
//            if (info.isPrimaryKey()) {
//                if (ConfigUtil.getConfiguration().getIdStrategy() == null || ConfigUtil.getConfiguration().getIdStrategy() == IdStrategy.AUTO) {
//                    sb.append(Constant.SPACE_4).append("@Id\n");
//                    sb.append(Constant.SPACE_4).append("@GeneratedValue(strategy = GenerationType.IDENTITY)\n");
//                } else if (ConfigUtil.getConfiguration().getIdStrategy() == IdStrategy.UUID) {
//                    sb.append(Constant.SPACE_4).append("@Id\n");
//                    sb.append(Constant.SPACE_4).append("@GeneratedValue(generator = \"uuidGenerator\")\n");
//                    sb.append(Constant.SPACE_4).append("@GenericGenerator(name = \"uuidGenerator\", strategy = \"uuid\")\n");
//                }
//            }
//            sb.append(Constant.SPACE_4).append(String.format("@Column(name = \"%s\")\n", info.getColumnName()));
//        }
//    }

}
