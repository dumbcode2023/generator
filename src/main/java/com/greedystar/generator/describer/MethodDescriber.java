package com.greedystar.generator.describer;

import java.util.List;

public class MethodDescriber {

    private String name;

    private String modifier;

    private List<ClassDescriber> params;

    private ClassDescriber returnType;

    private List<ClassDescriber> annotations;

    private String comments;

    public List<ClassDescriber> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<ClassDescriber> annotations) {
        this.annotations = annotations;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public List<ClassDescriber> getParams() {
        return params;
    }

    public void setParams(List<ClassDescriber> params) {
        this.params = params;
    }

    public ClassDescriber getReturnType() {
        return returnType;
    }

    public void setReturnType(ClassDescriber returnType) {
        this.returnType = returnType;
    }
}
