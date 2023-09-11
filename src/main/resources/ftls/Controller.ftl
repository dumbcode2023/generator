package ${Configuration.packageName}.${Configuration.path.controller};

import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};
${ServiceImport}
<#if Configuration.swaggerEnable>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<#if Configuration.responseInfoEnable>
import ${Configuration.packageName}.Response;
</#if>

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.swaggerEnable>
@Api(value = "/${EntityName}", tags = "${EntityName}管理接口")
</#if>
@RestController
@RequestMapping(value = "/${EntityName}")
public class ${ControllerClassName} {
    @Autowired
    private ${ServiceClassName} ${ServiceEntityName};

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "查询${EntityName}列表", httpMethod = "GET")
    </#if>
    @GetMapping(value = "")
    public <#if Configuration.responseInfoEnable>Response<List<${ClassName}>><#else>Object</#if> list() {
        <#if Configuration.mybatisPlusEnable><#-- mybatis-plus模式 -->
            List<${ClassName}> ${EntityName}s = ${ServiceEntityName}.list();
        <#else><#-- mybatis或jpa模式 -->
            List<${ClassName}> ${EntityName}s = ${ServiceEntityName}.findAll();
        </#if>
        <#if Configuration.responseInfoEnable>
            Response result = Response.ok(${EntityName}s);
        <#else>
            Map<String, Object> result = new HashMap<>();
            result.put("data", ${EntityName}s);
            result.put("status", 200);
            result.put("message", "OK");
        </#if>
        return result;
    }

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "查看${EntityName}详情", httpMethod = "GET")
    </#if>
    @GetMapping(value = "/{id}")
    public <#if Configuration.responseInfoEnable>Response<${ClassName}><#else> Object </#if> get(@PathVariable("id") ${pkType} id) {
        <#if Configuration.mybatisPlusEnable><#-- mybatis-plus模式 -->
            ${ClassName} ${EntityName} = ${ServiceEntityName}.getById(id);
        <#else><#-- mybatis或jpa模式 -->
            ${ClassName} ${EntityName} = ${ServiceEntityName}.get(id);
        </#if>
        <#if Configuration.responseInfoEnable>
            Response result = Response.ok(${EntityName});
        <#else>
            Map<String, Object> result = new HashMap<>();
            result.put("data", ${EntityName});
            result.put("status", 200);
            result.put("message", "OK");
        </#if>
        return result;
    }

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "创建${EntityName}", httpMethod = "POST")
    </#if>
    @PostMapping(value = "")
    public <#if Configuration.responseInfoEnable> Response<?><#else> Object </#if> post(@RequestBody ${ClassName} ${EntityName}) {
        <#if Configuration.responseInfoEnable>
           <#if Configuration.mybatisPlusEnable><#-- mybatis-plus模式 -->
            ${ServiceEntityName}.save(${EntityName});
           <#else><#-- mybatis或jpa模式 -->
            ${ServiceEntityName}.insert(${EntityName});
           </#if>
            return Response.ok();
        <#else>
            Map<String, Object> result = new HashMap<>();
            try {
                <#if Configuration.mybatisPlusEnable>
                    <#-- mybatis-plus模式 -->
                    ${ServiceEntityName}.save(${EntityName});
                <#else>
                    <#-- mybatis或jpa模式 -->
                    ${ServiceEntityName}.insert(${EntityName});
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

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "修改${EntityName}信息", httpMethod = "PUT")
    </#if>
    @PutMapping(value = "")
    public <#if Configuration.responseInfoEnable> Response<?><#else> Object </#if> put(@RequestBody ${ClassName} ${EntityName}) {
        <#if Configuration.responseInfoEnable>
            <#if Configuration.mybatisPlusEnable>
                <#-- mybatis-plus模式 -->
                ${ServiceEntityName}.updateById(${EntityName});
            <#else>
                <#-- mybatis或jpa模式 -->
                ${ServiceEntityName}.update(${EntityName});
            </#if>
            return Response.ok();
        <#else>
            Map<String, Object> result = new HashMap<>();
            try {
                <#if Configuration.mybatisPlusEnable><#-- mybatis-plus模式 -->
                    ${ServiceEntityName}.updateById(${EntityName});
                <#else><#-- mybatis或jpa模式 -->
                    ${ServiceEntityName}.update(${EntityName});
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


    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "删除${EntityName}", httpMethod = "DELETE")
    </#if>
    @DeleteMapping(value = "")
    public <#if Configuration.responseInfoEnable> Response<?><#else> Object </#if> delete(@RequestBody ${ClassName} ${EntityName}) {
        <#if Configuration.responseInfoEnable>
            <#if Configuration.mybatisPlusEnable>
                <#-- mybatis-plus模式 -->
                ${ServiceEntityName}.removeById(${EntityName}.getId());
            <#else>
                <#-- mybatis或jpa模式 -->
                ${ServiceEntityName}.delete(${EntityName});
            </#if>
            return Response.ok();
        <#else>
            Map<String, Object> result = new HashMap<>();
            try {
                <#if Configuration.mybatisPlusEnable>
                    <#-- mybatis-plus模式 -->
                    ${ServiceEntityName}.removeById(${EntityName}.getId());
                <#else>
                    <#-- mybatis或jpa模式 -->
                    ${ServiceEntityName}.delete(${EntityName});
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
