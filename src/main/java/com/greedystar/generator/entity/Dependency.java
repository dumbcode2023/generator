package com.greedystar.generator.entity;

public class Dependency {

    public static final String ALIAS_KNIFE4J = "knife4j";

    public static final String ALIAS_LOMBOK = "lombok";

    public static final String ALIAS_SWAGGER = "swagger";

    public static final String ALIAS_MYBATIS_PLUS = "mybatisPlus";
    public static final String ALIAS_MYSQL = "mysql";

    public static final String ALIAS_JPA = "jpa";

    private String alias;

    private boolean enabled = true;

    private String artifactId;

    private String version;

    private String groupId;

    public Dependency() {
    }

    public Dependency(String alias, String groupId, String artifactId, String version) {
        this.alias = alias;
        this.artifactId = artifactId;
        this.version = version;
        this.groupId = groupId;
    }

    public Dependency(String alias, boolean enabled, String groupId, String artifactId, String version) {
        this.alias = alias;
        this.enabled = enabled;
        this.artifactId = artifactId;
        this.version = version;
        this.groupId = groupId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


}
