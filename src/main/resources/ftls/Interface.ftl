package ${classInfo.packageName};

import ${entity.packageName}.${entity.name};
<#if Configuration.dependencies.mybatisPlus??>
import com.baomidou.mybatisplus.extension.service.IService;
<#else>
import java.io.Serializable;
</#if>
import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.dependencies.mybatisPlus??>
public interface ${classInfo.name} extends IService<${entity.name}> {

}
<#else><#-- mybatis或jpa模式 -->
public interface ${classInfo.name} {

    <#if Configuration.jpaEnable><#-- jpa模式 -->
    ${entity.name} get(Serializable id);

    List<${entity.name}> findAll();

    ${entity.name} insert(${entity.name} ${EntityName});

    List<${entity.name}> insertBatch(List<${entity.name}> ${EntityName}s);

    ${entity.name} update(${entity.name} ${EntityName});

    void delete(${entity.name} ${EntityName});

    <#else><#-- mybatis模式 -->
    ${entity.name} get(Serializable id);

    List<${entity.name}> findAll();

    int insert(${entity.name} ${EntityName});

    int insertBatch(List<${entity.name}> ${EntityName}s);

    int update(${entity.name} ${EntityName});

    int delete(${entity.name} ${EntityName});

    </#if>
}
</#if>
