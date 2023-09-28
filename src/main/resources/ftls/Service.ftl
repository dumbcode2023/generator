package ${classInfo.packageName};

import ${dao.packageName}.${dao.name};
import ${entity.packageName}.${entity.name};
${InterfaceImport}
<#if Configuration.dependencies.mybatisPlus??>
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<#else>
import java.io.Serializable;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
@Service
<#if Configuration.dependencies.mybatisPlus??><#-- mybatis-plus模式 -->
public class ${classInfo.name} extends ServiceImpl<${dao.name}, ${entity.name}> ${Implements} {

}
<#else><#-- mybatis或jpa模式 -->
public class ${ServiceClassName} ${Implements} {
    @Autowired
    private ${dao.name?uncap_first} ${dao.name?uncap_first};
    <#if Configuration.jpaEnable><#-- jpa模式 -->
    ${Override}
    public ${entity.name} get(Serializable id) {
        return ${dao.name?uncap_first}.findById(id).orElse(null);
    }
    ${Override}
    public List<${entity.name}> findAll() {
        return ${dao.name?uncap_first}.findAll();
    }
    ${Override}
    public ${entity.name} insert(${entity.name} ${entity.name?uncap_first}) {
        return ${dao.name?uncap_first}.save(${entity.name?uncap_first});
    }
    ${Override}
    public List<${entity.name}> insertBatch(List<${entity.name}> ${entity.name?uncap_first}s){
        return ${dao.name?uncap_first}.saveAll(${entity.name?uncap_first}s);
    }
    ${Override}
    public ${entity.name} update(${entity.name} ${entity.name?uncap_first}) {
        return ${dao.name?uncap_first}.save(${entity.name?uncap_first});
    }
    ${Override}
    public void delete(${entity.name} ${entity.name?uncap_first}) {
        ${dao.name?uncap_first}.delete(${entity.name?uncap_first});
    }
    <#else><#-- mybatis模式 -->
    ${Override}
    public ${entity.name} get(Serializable id) {
        return ${dao.name?uncap_first}.get(id);
    }
    ${Override}
    public List<${entity.name}> findAll() {
        return ${dao.name?uncap_first}.findAll();
    }
    ${Override}
    public int insert(${entity.name} ${entity.name?uncap_first}) {
        return ${dao.name?uncap_first}.insert(${entity.name?uncap_first});
    }
    ${Override}
    public int insertBatch(List<${entity.name}> ${entity.name?uncap_first}s) {
        return ${dao.name?uncap_first}.insertBatch(${entity.name?uncap_first}s);
    }
    ${Override}
    public int update(${entity.name} ${entity.name?uncap_first}) {
        return ${dao.name?uncap_first}.update(${entity.name?uncap_first});
    }
    ${Override}
    public int delete(${entity.name} ${entity.name?uncap_first}) {
        return ${dao.name?uncap_first}.delete(${entity.name?uncap_first});
    }
    </#if>
}
</#if>