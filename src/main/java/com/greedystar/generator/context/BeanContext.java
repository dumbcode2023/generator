package com.greedystar.generator.context;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.greedystar.generator.entity.Constant.PLACEHOLDER;

public class BeanContext {

    private DomainContext domainContext;

    private String code;

    /**
     * 相对路径
     */
    private String path;

    private String format;

    public BeanContext() {
    }

    public BeanContext(String path, String format) {
        this.path = path;
        this.format = format;
    }

    private static Map<Type, BeanContext> configMap = new LinkedHashMap<>();

    /**
     * 配置默认bean生成模板配置
     */
    static {
        configMap.put(Type.CONTROLLER, new BeanContext("controller", PLACEHOLDER + "Controller"));
        configMap.put(Type.SERVICE, new BeanContext("service", PLACEHOLDER + "Service"));
        configMap.put(Type.SERVICE_IMPL, new BeanContext("serviceImpl", PLACEHOLDER + "ServiceImpl"));
        configMap.put(Type.DAO, new BeanContext("dao", PLACEHOLDER + "Dao"));
        configMap.put(Type.ENTITY, new BeanContext("entity", PLACEHOLDER));
        configMap.put(Type.MAPPER, new BeanContext("mapper", PLACEHOLDER + "Mapper"));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public static BeanContext getConfig(Type type){
        return configMap.get(type);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DomainContext getDomainContext() {
        return domainContext;
    }

    public void setDomainContext(DomainContext domainContext) {
        this.domainContext = domainContext;
    }

    public enum Type {
        CONTROLLER("controller"),
        SERVICE("service"),
        ENTITY("entity"),
        DAO("dao"),
        MAPPER("mapper"),
        CONFIG("config"),
        SERVICE_IMPL("serviceImpl");
        private String code;

        Type(String code) {
            this.code = code;
        }
    }
}
