package com.greedystar.generator.invoker;

import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.invoker.base.AbstractBuilder;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.utils.StringUtil;

/**
 * @author GreedyStar
 * @since 2018/9/5
 */
public class SingleInvoker extends AbstractInvoker {

    private SingleInvoker() {

    }

    @Override
    protected void queryMetaData() throws Exception {
        DomainContext domainContext = getDomainContext();
        domainContext.setColumnInfoList(connectionUtil.getMetaData(domainContext.getTableName()));
    }

    @Override
    protected void initTasks() {
        taskQueue.initSingleTasks(this);
    }

    public static class Builder extends AbstractBuilder {

        public Builder() {
            invoker = new SingleInvoker();
        }

        public Builder setTableName(String tableName) {
            invoker.getDomainContext().setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.getDomainContext().setClassName(className);
            return this;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isEmpty(invoker.getDomainContext().getTableName())) {
                throw new Exception("Table name can't be null.");
            }
            if (StringUtil.isEmpty(invoker.getDomainContext().getClassName())) {
                invoker.getDomainContext().setClassName(StringUtil.tableName2ClassName(invoker.getDomainContext().getTableName()));
            }
        }
    }

}
