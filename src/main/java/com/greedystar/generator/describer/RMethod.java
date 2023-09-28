package com.greedystar.generator.describer;

import java.util.List;

public class RMethod {

    private String name;

    private String modifier;

    private List<RClass> params;

    private RClass returnType;

    private List<RClass> annotations;

    private String comments;

    public List<RClass> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<RClass> annotations) {
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

    public List<RClass> getParams() {
        return params;
    }

    public void setParams(List<RClass> params) {
        this.params = params;
    }

    public RClass getReturnType() {
        return returnType;
    }

    public void setReturnType(RClass returnType) {
        this.returnType = returnType;
    }
}
