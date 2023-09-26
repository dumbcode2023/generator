package com.greedystar.generator.context;

import com.greedystar.generator.ProjectObserver;
import com.greedystar.generator.entity.Dependency;
import com.greedystar.generator.entity.IdStrategy;

import java.util.*;

import static com.greedystar.generator.entity.Dependency.*;

/**
 * Generator 配置类
 *
 * @author GreedyStar
 * @since 2018/9/7
 */
public class ProjectContext  {

    /**
     * 数据库配置
     */
    private Db db;

    /**
     * spring bean 配置
     */
    private List<BeanContext> beans;

    /**
     * 代码作者
     */
    private String author;

    /**
     * 项目实际生成路径
     */
    private String projectPath;

    /**
     * 文件覆盖
     */
    private boolean fileOverride;

    /**
     * 类型转换器全限定类名
     */
    private String convertor;

    /**
     * 是否将mybatis的xml映射文件放在源文件目录下
     */
    private boolean mapperUnderSource;

    /**
     * id策略（auto：数据库自增，uuid：生成uuid）
     */
    private IdStrategy idStrategy;

    private String groupId;

    private String artifactId;

    private String version;

    /**
     * 顶级包名
     */
    private String packageName;
    /**
     * 是否生成统一回复、统一异常处理类
     */
    private boolean responseInfoEnable;

    private Dependency parent;

    private static Map<String, String> conflicts = new HashMap<>();

    public ProjectContext() {
        //初始化默认依赖
        dependencies.put(ALIAS_KNIFE4J, new Dependency(ALIAS_KNIFE4J,
                "com.github.xiaoymin", "knife4j-spring-boot-autoconfigure", "3.0.3"));
        dependencies.put(ALIAS_LOMBOK, new Dependency(ALIAS_LOMBOK,
                "org.projectlombok", "lombok", "1.18.22"));
//        dependencies.put(ALIAS_SWAGGER, new Dependency(ALIAS_SWAGGER,
//                "io.springfox", "springfox-boot-starter", "3.0.0"));
        dependencies.put(ALIAS_MYBATIS_PLUS, new Dependency(ALIAS_MYBATIS_PLUS,
                "com.baomidou", "mybatis-plus-boot-starter", "3.5.2"));
//        dependencies.put(ALIAS_JPA, new Dependency(ALIAS_JPA,
//                "com.baomidou","mybatis-plus-boot-starter","3.5.2"));
    }

    public ProjectContext(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public Map<String, Dependency> dependencies = new LinkedHashMap<>();

    static {
        //初始化冲突依赖组合
        conflicts.put(ALIAS_KNIFE4J, ALIAS_SWAGGER);
        conflicts.put(ALIAS_SWAGGER, ALIAS_KNIFE4J);

        conflicts.put(ALIAS_JPA, ALIAS_MYBATIS_PLUS);
        conflicts.put(ALIAS_MYBATIS_PLUS, ALIAS_JPA);
    }
    private List<ProjectObserver> observers = new ArrayList<>();

    public void register(ProjectObserver describer) {
        observers.add(describer);
    }

    public void publishEvent(Integer eventType, Object data) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onEvent(eventType, data);
        }
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isResponseInfoEnable() {
        return responseInfoEnable;
    }

    public void setResponseInfoEnable(boolean responseInfoEnable) {
        this.responseInfoEnable = responseInfoEnable;
    }

    public Boolean getResponseInfoEnable() {
        return responseInfoEnable;
    }

    public void setResponseInfoEnable(Boolean responseInfoEnable) {
        this.responseInfoEnable = responseInfoEnable;
    }

    public Dependency getParent() {
        return parent;
    }

    public void setParent(Dependency parent) {
        this.parent = parent;
    }

    public void putDependency(String alias, Dependency dependency) {
        dependencies.put(alias, dependency);
    }

    public Dependency getDependency(String alias) {
        return dependencies.get(alias);
    }

    public void setDependencies(Map<String, Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getConflicts() {
        return conflicts;
    }

    public void setConflicts(Map<String, String> conflicts) {
        this.conflicts = conflicts;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getConvertor() {
        return convertor;
    }

    public void setConvertor(String convertor) {
        this.convertor = convertor;
    }


    public boolean isMapperUnderSource() {
        return mapperUnderSource;
    }

    public void setMapperUnderSource(boolean mapperUnderSource) {
        this.mapperUnderSource = mapperUnderSource;
    }


    public boolean isFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public IdStrategy getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(IdStrategy idStrategy) {
        this.idStrategy = idStrategy;
    }

    public List<BeanContext> getBeans() {
        return beans;
    }

    public void setBeans(List<BeanContext> beans) {
        this.beans = beans;
    }

    public Map<String, Dependency> getDependencies() {
        return dependencies;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public void checkConflics() {
        for (Map.Entry<String, String> entry : getConflicts().entrySet()) {
            if (dependencies.get(entry.getKey()) != null && dependencies.get(entry.getValue()) != null) {
                throw new RuntimeException(String.format("Can not enable %s mode and %s at the same time.", entry.getKey(), entry.getValue()));
            }
        }
    }

    /**
     * 数据库配置
     */
    public static class Db {
        /**
         * 数据库URL
         */
        private String url;
        /**
         * 数据库用户名
         */
        private String username;
        /**
         * 数据库密码
         */
        private String password;

        public Db() {
        }

        public Db(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
}
