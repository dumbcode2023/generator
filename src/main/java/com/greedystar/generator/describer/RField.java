package com.greedystar.generator.describer;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;

import java.util.ArrayList;
import java.util.List;

import static com.greedystar.generator.utils.StringUtil.firstToLowerCase;

public class RField {

    private String name;

    /**
     * 修饰符，默认为private
     */
    private String modifier = "private";

    private RClass classType;

    private List<String> annotations = new ArrayList<>();

    private String comment;

    public RField(String name) {
        this.name = name;
    }

    public static RField of(String name) {
        return new RField(name);
    }

    public static RField ofBean(BeanContext bean) {
        DomainContext domain = bean.getDomainContext();
        String serviceClassName = bean.formatClassName();
        RField field = new RField(firstToLowerCase(serviceClassName));
        field.setClassType(RClass.of(domain.getProjectCtx().getPackageName() + bean.getPath(), serviceClassName));
        return field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public RClass getClassType() {
        return classType;
    }

    public void setClassType(RClass classType) {
        this.classType = classType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }
}
