package com.greedystar.generator.context;

import com.greedystar.generator.entity.ColumnInfo;

import java.util.List;

public class DomainContext {
    /**
     * 主表名
     */
    protected String tableName;
    /**
     * 主类名
     */
    protected String className;
    /**
     * 父表名
     */
    protected String parentTableName;
    /**
     * 父类名
     */
    protected String parentClassName;
    /**
     * 外键列名
     */
    protected String foreignKey;
    /**
     * 关系表名
     */
    protected String relationalTableName;
    /**
     * 父表外键列名
     */
    protected String parentForeignKey;
    /**
     * 主表元数据
     */
    protected List<ColumnInfo> columnInfoList;
    /**
     * 父表元数据
     */
    protected List<ColumnInfo> parentColumnInfoList;
    /**
     * 表注释
     */
    private String comment;

    private ProjectContext projectCtx;

    public ProjectContext getProjectCtx() {
        return projectCtx;
    }

    public void setProjectCtx(ProjectContext projectCtx) {
        this.projectCtx = projectCtx;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParentTableName() {
        return parentTableName;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getRelationalTableName() {
        return relationalTableName;
    }

    public void setRelationalTableName(String relationalTableName) {
        this.relationalTableName = relationalTableName;
    }

    public String getParentForeignKey() {
        return parentForeignKey;
    }

    public void setParentForeignKey(String parentForeignKey) {
        this.parentForeignKey = parentForeignKey;
    }

    public List<ColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<ColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }

    public List<ColumnInfo> getParentColumnInfoList() {
        return parentColumnInfoList;
    }

    public void setParentColumnInfoList(List<ColumnInfo> parentColumnInfoList) {
        this.parentColumnInfoList = parentColumnInfoList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
