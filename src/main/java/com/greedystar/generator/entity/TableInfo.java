package com.greedystar.generator.entity;

import java.util.List;

public class TableInfo {
    /**
     * 表备注
     */
    private String comment;

    private String name;
    private String pkName;
    private String pkType;

    public TableInfo() {
    }

    public TableInfo(String name, List<ColumnInfo> list) {
        this.name = name;
        this.columns = list;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getPkType() {
        return pkType;
    }

    public void setPkType(String pkType) {
        this.pkType = pkType;
    }

    private List<ColumnInfo> columns;

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

    public List<ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnInfo> columns) {
        this.columns = columns;
    }

    public void genPkType() {
        for(ColumnInfo columnInfo:columns){
            if(columnInfo.isPrimaryKey()){
                this.pkType = columnInfo.getPropertyType();
            }
        }
    }
}
