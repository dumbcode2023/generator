package ${Configuration.packageName}.${classInfo.packageName};


<#if Configuration.dependencies['lombok']?? >
import lombok.Data;
</#if>

<#if Configuration.dependencies.mybatisPlus??>
import com.baomidou.mybatisplus.annotation.*;
<#elseif Configuration.dependencies.jpa??>
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
</#if>
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ${classInfo.comment}
 *
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.dependencies.lombok??>
@Data
</#if>
<#if Configuration.dependencies.mybatisPlus??>
@TableName(value = "${TableName}")
<#elseif Configuration.jpaEnable>
@Entity
@Table(name = "${TableName}")
</#if>
public class ${classInfo.className} implements Serializable {

    <#list classInfo.fieldList as field>
    /**
    * ${field.comment}
    *
    */
    <#list field.annotations as annotation>
    ${annotation}
    </#list>
    ${field.modifier} ${field.classType.className} ${field.name};

    </#list>
    <#if !Configuration.dependencies.lombok??>
       <#list classInfo.fieldList as field>
           public ${field.classType.className} get${field.name?cap_first}(){
                return this.${field.name};
           }
           public void set${field.name?cap_first}( ${field.classType.className} ${field.name}){
                this.${field.name} = ${field.name};
           }
       </#list>
   </#if>
}