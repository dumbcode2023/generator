package com.greedystar.generator.utils;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.entity.Mode;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.task.*;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.task.entity.EntityTask;

import java.util.LinkedList;

/**
 * @author GreedyStar
 * @since 2018-11-27
 */
public class TaskQueue {

    /**
     * 任务队列
     */
    private LinkedList<AbstractTask> taskQueue = new LinkedList<>();

    /**
     * 初始化共性任务，包括Controller、ServiceImpl、Service、Dao、Mapper任务
     *
     * @param invoker 执行器
     */
    private void initCommonTasks(AbstractInvoker invoker) {
        DomainContext domainContext = invoker.getDomainContext();

        if (BeanContext.getConfig(BeanContext.Type.CONTROLLER) != null) {
            taskQueue.add(new ControllerTask(domainContext));
        }
        if (BeanContext.getConfig(BeanContext.Type.SERVICE_IMPL) != null) {
            taskQueue.add(new ServiceTask(invoker.getDomainContext()));
        }
        if (BeanContext.getConfig(BeanContext.Type.SERVICE) != null) {
            taskQueue.add(new InterfaceTask(domainContext));
        }
        if (BeanContext.getConfig(BeanContext.Type.DAO) != null) {
            taskQueue.add(new DaoTask(invoker.getDomainContext()));
        }
//        if (BeanConfig.getConfig(BeanConfig.Type.MAPPER)!=null) {
//            taskQueue.add(new MapperTask(invoker));
//        }
        if (domainContext.getProjectCtx() != null) {
            taskQueue.add(new ProjectStructureTask());
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param invoker 执行器
     */
    public void initSingleTasks(AbstractInvoker invoker) {
        initCommonTasks(invoker);
        BeanContext bean = BeanContext.getConfig(BeanContext.Type.ENTITY);
        if (bean != null) {
            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker.getDomainContext()));
        }
    }

//    /**
//     * 初始化单表生成任务，包括Entity、Mapper任务
//     *
//     * @param invoker 执行器
//     */
//    public void initMany2OneTasks(AbstractInvoker invoker) {
//        initCommonTasks(invoker);
//        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getEntity())) {
//            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker));
//            taskQueue.add(new EntityTask(Mode.ENTITY_PARENT, invoker));
//        }
//    }
//
//    /**
//     * 初始化单表生成任务，包括Entity、Mapper任务
//     *
//     * @param invoker 执行器
//     */
//    public void initOne2ManyTasks(AbstractInvoker invoker) {
//        initCommonTasks(invoker);
//        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getEntity())) {
//            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker));
//            taskQueue.add(new EntityTask(Mode.ENTITY_PARENT, invoker));
//        }
//    }
//
//    /**
//     * 初始化单表生成任务，包括Entity、Mapper任务
//     *
//     * @param invoker 执行器
//     */
//    public void initMany2ManyTasks(AbstractInvoker invoker) {
//        initCommonTasks(invoker);
//        if (!StringUtil.isEmpty(ConfigUtil.getConfiguration().getPath().getEntity())) {
//            taskQueue.add(new EntityTask(Mode.ENTITY_MAIN, invoker));
//            taskQueue.add(new EntityTask(Mode.ENTITY_PARENT, invoker));
//        }
//    }

    /**
     * 任务队列是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    /**
     * 取出一个任务
     *
     * @return 任务
     */
    public AbstractTask poll() {
        return taskQueue.poll();
    }

}
