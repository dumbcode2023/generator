package com.greedystar.generator.invoker.base;

import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.db.ConnectionUtil;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.TaskQueue;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GreedyStar
 * @since 2018/9/5
 */
public abstract class AbstractInvoker implements Invoker {

    private DomainContext domainContext;

    /**
     * 数据库连接工具
     */
    protected ConnectionUtil connectionUtil = new ConnectionUtil();
    /**
     * 任务队列
     */
    protected TaskQueue taskQueue = new TaskQueue();
    /**
     * 线程池
     */
    private ExecutorService executorPool = Executors.newFixedThreadPool(6);

    /**
     * 获取表元数据，模板方法，由子类实现
     *
     * @throws Exception 获取元数据失败则抛出异常
     */
    protected abstract void queryMetaData() throws Exception;

    /**
     * 初始化代码生成任务，模板方法，由子类实现
     */
    protected abstract void initTasks();

    /**
     * 开始生成代码
     */
    @Override
    public void execute() {
        try {
            domainContext.setProjectCtx(ConfigUtil.getConfiguration());
            queryMetaData();
            initTasks();
            while (!taskQueue.isEmpty()) {
                AbstractTask task = taskQueue.poll();
                executorPool.execute(() -> {
                    try {
                        task.run();
                    } catch (IOException | TemplateException e) {
                        e.printStackTrace();
                    }
                });
            }
            executorPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DomainContext getDomainContext() {
        return domainContext;
    }

    public void setDomainContext(DomainContext domainContext) {
        this.domainContext = domainContext;
    }
}
