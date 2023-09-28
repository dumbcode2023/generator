package com.greedystar.generator.utils;

import com.greedystar.generator.context.BeanContext;
import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.invoker.base.AbstractInvoker;
import com.greedystar.generator.task.ControllerTask;
import com.greedystar.generator.task.DaoTask;
import com.greedystar.generator.task.InterfaceTask;
import com.greedystar.generator.task.ServiceImplTask;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.task.entity.EntityTask;
import com.greedystar.generator.task.project.ProjectStructureTask;

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

        BeanContext bean = BeanContext.getConfig(BeanContext.Type.ENTITY);
        if (bean != null) {
            bean.setDomainContext(invoker.getDomainContext());
            taskQueue.add(new EntityTask(bean));
        }

        DomainContext domainContext = invoker.getDomainContext();
        BeanContext dao = BeanContext.getConfig(BeanContext.Type.DAO);
        if (dao != null) {
            dao.setDomainContext(domainContext);
            taskQueue.add(new DaoTask(dao,bean));
        }

        BeanContext service = BeanContext.getConfig(BeanContext.Type.INTERF);
        if (service != null) {
            service.setDomainContext(domainContext);
            taskQueue.add(new InterfaceTask(service,bean));
        }

        BeanContext serviceImpl = BeanContext.getConfig(BeanContext.Type.SERVICE_IMPL);
        if (serviceImpl != null) {
            serviceImpl.setDomainContext(domainContext);
            taskQueue.add(new ServiceImplTask(serviceImpl, service, dao, bean));
        }

        BeanContext controller = BeanContext.getConfig(BeanContext.Type.CONTROLLER);
        if (controller != null) {
            controller.setDomainContext(domainContext);
            taskQueue.add(new ControllerTask(controller, service,bean));
        }

//        if (BeanConfig.getConfig(BeanConfig.Type.MAPPER)!=null) {
//            taskQueue.add(new MapperTask(invoker));
//        }
        if (domainContext.getProjectCtx() != null) {
            taskQueue.add(new ProjectStructureTask(domainContext));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param invoker 执行器
     */
    public void initSingleTasks(AbstractInvoker invoker) {
        initCommonTasks(invoker);

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
