package com.greedystar.generator.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TemplateUtil {

    private static Logger logger = Logger.getLogger(TemplateUtil.class.getName());

    /**
     * 模板路径
     */
    private static String path = new File(TemplateUtil.class.getClassLoader().getResource("ftls").getFile()).getPath();
    /**
     * freemarker配置
     */
    private static volatile Configuration configuration;
    public final static int TYPE_ENTITY = 0;
    public final static int TYPE_DAO = 1;
    public final static int TYPE_SERVICE = 2;
    public final static int TYPE_CONTROLLER = 3;
    public final static int TYPE_MAPPER = 4;
    public final static int TYPE_INTERFACE = 5;
    public final static int TYPE_POM = 6;
    public final static int TYPE_APPLICATION_FILE = 7;
    public final static int TYPE_BOOTSTRAP_CLASS = 8;
    public final static int TYPE_SWAGGER_CONFIG = 9;
    public final static int TYPE_BUSINESS_EXCEPTION = 10;
    public final static int TYPE_EXCEPTION_HANDLER = 11;
    public final static int TYPE_RESPONSE = 12;
    public static Configuration getInstance() {
        if (null == configuration) {
            synchronized (TemplateUtil.class) {
                if (null == configuration) {
                    configuration = new Configuration(Configuration.VERSION_2_3_23);
                    try {
                        if (path.contains("jar")) {
                            configuration.setClassForTemplateLoading(TemplateUtil.class, "/ftls");
                        } else {
                            configuration.setDirectoryForTemplateLoading(new File(path));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    configuration.setEncoding(Locale.CHINA, "utf-8");
                }
            }
        }
        return configuration;
    }

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
