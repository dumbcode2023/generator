package ${classInfo.packageName};

import ${entity.packageName}.${entity.name};
import ${service.packageName}.${service.name};
<#if Configuration.dependencies.mybatisPlus??>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${Configuration.packageName}.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.dependencies.swagger??>
@Api(value = "/${entity.name?uncap_first}", tags = "${entity.name?uncap_first}管理接口")
</#if>
@RestController
@RequestMapping(value = "/${entity.name?uncap_first}")
public class ${classInfo.name} {
    @Autowired
    private ${service.name} ${service.name?uncap_first};

    <#if Configuration.dependencies.swagger??>
    @ApiOperation(value = "查询${entity.name?uncap_first}列表", httpMethod = "GET")
    </#if>
    @GetMapping(value = "")
    public <#if Configuration.artifactId??>Response<List<${entity.name}>><#else>Object</#if> list() {
        <#if Configuration.dependencies.mybatisPlus??><#-- mybatis-plus模式 -->
            List<${entity.name}> ${entity.name?uncap_first}s = ${service.name?uncap_first}.list();
        <#else><#-- mybatis或jpa模式 -->
            List<${entity.name}> ${entity.name?uncap_first}s = ${service.name?uncap_first}.findAll();
        </#if>
        <#if Configuration.artifactId??>
            Response result = Response.ok(${entity.name?uncap_first}s);
        <#else>
            Map<String, Object> result = new HashMap<>();
            result.put("data", ${entity.name?uncap_first}s);
            result.put("status", 200);
            result.put("message", "OK");
        </#if>
        return result;
    }

    <#if Configuration.dependencies.swagger??>
    @ApiOperation(value = "查看${entity.name?uncap_first}详情", httpMethod = "GET")
    </#if>
    @GetMapping(value = "/{id}")
    public <#if Configuration.artifactId??>Response<${entity.name}><#else> Object </#if> get(@PathVariable("id") ${tableInfo.pkType} id) {
        <#if Configuration.dependencies.mybatisPlus??><#-- mybatis-plus模式 -->
            ${entity.name} ${entity.name?uncap_first} = ${service.name?uncap_first}.getById(id);
        <#else><#-- mybatis或jpa模式 -->
            ${entity.name} ${entity.name?uncap_first} = ${service.name?uncap_first}.get(id);
        </#if>
        <#if Configuration.artifactId??>
            Response result = Response.ok(${entity.name?uncap_first});
        <#else>
            Map<String, Object> result = new HashMap<>();
            result.put("data", ${entity.name?uncap_first});
            result.put("status", 200);
            result.put("message", "OK");
        </#if>
        return result;
    }

    <#if Configuration.dependencies.swagger??>
    @ApiOperation(value = "创建${entity.name?uncap_first}", httpMethod = "POST")
    </#if>
    @PostMapping(value = "")
    public  <#if Configuration.artifactId??> Response<?><#else> Object </#if> post(@RequestBody ${entity.name} ${entity.name?uncap_first}) {
        <#if Configuration.artifactId??>
           <#if Configuration.dependencies.mybatisPlus??><#-- mybatis-plus模式 -->
            ${service.name?uncap_first}.save(${entity.name?uncap_first});
           <#else><#-- mybatis或jpa模式 -->
            ${service.name?uncap_first}.insert(${entity.name?uncap_first});
           </#if>
            return Response.ok();
        <#else>
            Map<String, Object> result = new HashMap<>();
            try {
                <#if Configuration.dependencies.mybatisPlus??>
                    <#-- mybatis-plus模式 -->
                    ${service.name?uncap_first}.save(${entity.name?uncap_first});
                <#else>
                    <#-- mybatis或jpa模式 -->
                    ${service.name?uncap_first}.insert(${entity.name?uncap_first});
                </#if>
                result.put("status", 200);
                result.put("message", "OK");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", 500);
                result.put("message", "ERROR");
            }
            return result;
        </#if>
    }

    <#if Configuration.dependencies.swagger??>
    @ApiOperation(value = "修改${entity.name?uncap_first}信息", httpMethod = "PUT")
    </#if>
    @PutMapping(value = "")
    public <#if Configuration.artifactId??> Response<?><#else> Object </#if> put(@RequestBody ${entity.name} ${entity.name?uncap_first}) {
        <#if Configuration.artifactId??>
            <#if Configuration.dependencies.mybatisPlus??>
                <#-- mybatis-plus模式 -->
                ${service.name?uncap_first}.updateById(${entity.name?uncap_first});
            <#else>
                <#-- mybatis或jpa模式 -->
                ${service.name?uncap_first}.update(${entity.name?uncap_first});
            </#if>
            return Response.ok();
        <#else>
            Map<String, Object> result = new HashMap<>();
            try {
                <#if Configuration.dependencies.mybatisPlus??>
                    <#-- mybatis-plus模式 -->
                    ${service.name?uncap_first}.updateById(${entity.name?uncap_first});
                <#else><#-- mybatis或jpa模式 -->
                    ${service.name?uncap_first}.update(${entity.name?uncap_first});
                </#if>
                result.put("status", 200);
                result.put("message", "OK");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", 500);
                result.put("message", "ERROR");
            }
            return result;
        </#if>
    }


    <#if Configuration.dependencies.swagger??>
    @ApiOperation(value = "删除${entity.name?uncap_first}", httpMethod = "DELETE")
    </#if>
    @DeleteMapping(value = "")
    public <#if Configuration.artifactId??> Response<?><#else> Object </#if> delete(@RequestBody ${entity.name} ${entity.name?uncap_first}) {
        <#if Configuration.artifactId??>
            <#if Configuration.dependencies.mybatisPlus??>
                <#-- mybatis-plus模式 -->
                ${service.name?uncap_first}.removeById(${entity.name?uncap_first}.getId());
            <#else>
                <#-- mybatis或jpa模式 -->
                ${service.name?uncap_first}.delete(${entity.name?uncap_first});
            </#if>
            return Response.ok();
        <#else>
            Map<String, Object> result = new HashMap<>();
            try {
                <#if Configuration.dependencies.mybatisPlus??>
                    <#-- mybatis-plus模式 -->
                    ${service.name?uncap_first}.removeById(${entity.name?uncap_first}.getId());
                <#else>
                    <#-- mybatis或jpa模式 -->
                    ${service.name?uncap_first}.delete(${entity.name?uncap_first});
                </#if>
                result.put("status", 200);
                result.put("message", "OK");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", 500);
                result.put("message", "ERROR");
            }
            return result;
        </#if>
    }

}
