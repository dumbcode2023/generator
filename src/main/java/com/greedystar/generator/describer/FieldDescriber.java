package com.greedystar.generator.describer;

import java.util.ArrayList;
import java.util.List;

public class FieldDescriber {

    private String name;

    private String modifier;

    private ClassDescriber classType;

    private List<String> annotations = new ArrayList<>();

    private String comment;

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

    public ClassDescriber getClassType() {
        return classType;
    }

    public void setClassType(ClassDescriber classType) {
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
