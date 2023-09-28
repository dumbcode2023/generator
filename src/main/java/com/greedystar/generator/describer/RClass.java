package com.greedystar.generator.describer;

import java.util.List;

public class RClass implements Describer {

    private String name;

    private String packageName;

    /**
     * 注释信息
     */
    private String comment;

    private RField[] fields;

    private List<RMethod> methodList;

    private List<String> imports;

    public RClass(String name) {
        this.name = name;
    }

    public RClass(String packageName, String className) {
        this.packageName = packageName;
        this.name = className;
    }

    public RClass(String name, RField... fields) {
        this.name = name;
        this.fields = fields;
    }

    public RClass(String packageName, String name, RField... fields) {
        this.packageName = packageName;
        this.name = name;
        this.fields = fields;
    }

    public static RClass of(String className) {
        return new RClass(className);
    }

    public static RClass of(String packageName, String className) {
        return new RClass(packageName, className);
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public RField[] getFields() {
        return fields;
    }

    public void setFields(RField... fields) {
        this.fields = fields;
    }

    public List<RMethod> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<RMethod> methodList) {
        this.methodList = methodList;
    }

    public static class Builder {
        private RClass rClass;

        public RClass build() {
            return rClass;
        }
    }
}
