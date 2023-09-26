package com.greedystar.generator.task.base;


import com.greedystar.generator.ProjectObserver;
import com.greedystar.generator.describer.ClassDescriber;

import java.util.logging.Logger;

public abstract class AbstractClassTask extends AbstractTask implements ProjectObserver {
    protected ClassDescriber describer;

    public void setDescriber(ClassDescriber describer) {
        this.describer = describer;
    }

    public ClassDescriber getDescriber() {
        return describer;
    }

    private Logger logger = Logger.getLogger(AbstractClassTask.class.getName());

//    protected void generator(Map<String, Object> data) throws IOException, TemplateException {
//        Template tpl = FreemarkerConfigUtil.getInstance().getTemplate(getTemplate());
//        File file = getDest();
//        // 已存在的文件不予覆盖
//        if (file.exists() && !ConfigUtil.getConfiguration().isFileOverride()) {
//            if (logger.isLoggable(Level.INFO)) {
//                logger.info(String.format("%s already exit. Generating %s.generated", file.getName(), file.getName()));
//            }
//            file = new File(file.getAbsolutePath() + ".generated");
//        } else {
//            if (logger.isLoggable(Level.INFO)) {
//                logger.info(String.format("Generating %s %n", file.getName()));
//            }
//        }
//        // 自动创建代码生成路径目录
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        // 填充数据写入文件
//        FileOutputStream fos = new FileOutputStream(file);
//        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
//        BufferedWriter bw = new BufferedWriter(osw, 1024);
//        tpl.process(data, bw);
//        bw.close();
//    }

    protected abstract String getTemplate();

}
