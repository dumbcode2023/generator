package com.greedystar.generator.task.base;

import java.util.logging.Logger;

public abstract class AbstractJavaTask extends AbstractTask {

    private Logger logger = Logger.getLogger(AbstractJavaTask.class.getName());

    protected abstract String getTemplate();

    public AbstractJavaTask() {

    }


}
