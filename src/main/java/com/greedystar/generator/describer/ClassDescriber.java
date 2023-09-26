package com.greedystar.generator.describer;

import java.util.List;

public class ClassDescriber implements Describer {

    private String className;

    private String packageName;

    /**
     * 注释信息
     */
    private String comment;

    private Type type;

//    private List<String> annotations;

    private List<FieldDescriber> fieldList;

    private List<MethodDescriber> methodList;

    public enum Type{
        INTERFACE,ABSTRACT_CLASS,ANNOTATION,ENUM,CLASS;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

//    public List<String> getAnnotations() {
//        return annotations;
//    }
//
//    public void setAnnotations(List<String> annotations) {
//        this.annotations = annotations;
//    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<FieldDescriber> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldDescriber> fieldList) {
        this.fieldList = fieldList;
    }

    public List<MethodDescriber> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<MethodDescriber> methodList) {
        this.methodList = methodList;
    }

}
