package ${classInfo.packageName};


<#if Configuration.dependencies['lombok']?? >
import lombok.Data;
</#if>

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ${classInfo.comment!''}
 *
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.dependencies.lombok??>
@Data
</#if>
public class ${classInfo.className} implements Serializable {

    <#list classInfo.fields as field>
    /**
    * ${field.comment}
    *
    */
    ${field.modifier} ${field.classType.className} ${field.name};

    </#list>
    <#if !Configuration.dependencies.lombok??>
       <#list classInfo.fields as field>
           public ${field.classType.className} get${field.name?cap_first}(){
                return this.${field.name};
           }
           public void set${field.name?cap_first}( ${field.classType.className} ${field.name}){
                this.${field.name} = ${field.name};
           }
       </#list>
   </#if>
}