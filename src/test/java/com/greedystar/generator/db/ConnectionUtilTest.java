package com.greedystar.generator.db;

import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.entity.ColumnInfo;
import org.junit.Test;

import java.util.List;

public class ConnectionUtilTest {

    @Test
    public void initConnection() throws Exception {
        ProjectContext projectContext = new ProjectContext();
        ProjectContext.Db db = new ProjectContext.Db();
        db.setUrl("jdbc:mysql://localhost:3306/test?useSSL=true&serverTimezone=UTC");
        db.setUsername("root");
        db.setPassword("12345678");
        projectContext.setDb(db);
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connectionUtil.initConnection();
        List<ColumnInfo> columnInfoList = connectionUtil.getMetaData("student").getColumns();
        assert columnInfoList!=null;
    }

    @Test
    public void getMetaData() {

    }
}