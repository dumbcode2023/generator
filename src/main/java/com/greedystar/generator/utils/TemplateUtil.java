package com.greedystar.generator.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TemplateUtil {

    private static Logger logger = Logger.getLogger(TemplateUtil.class.getName());

    public static void render(Map<String, Object> data, Template tpl, File file) {
        // 已存在的文件不予覆盖
        if (file.exists() && !ConfigUtil.getConfiguration().isFileOverride()) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("%s already exit. Generating %s.generated", file.getName(), file.getName()));
            }
            file = new File(file.getAbsolutePath() + ".generated");
        } else {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Generating %s %n", file.getName()));
            }
        }
        // 自动创建代码生成路径目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 填充数据写入文件
        try (OutputStreamWriter osw = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8)) {
            tpl.process(data, osw);
            logger.info("generator a file path=" + file.getAbsolutePath());
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
