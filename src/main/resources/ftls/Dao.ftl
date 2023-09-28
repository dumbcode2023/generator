package ${classInfo.packageName};

import ${entity.packageName}.${ClassName};
<#if Configuration.dependencies.mybatisPlus??>
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
<#elseif Configuration.dependencies.jpa??>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<#else>
import org.apache.ibatis.annotations.Mapper;
</#if>

import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.dependencies.mybatisPlus??>
@Mapper
public interface ${classInfo.name} extends BaseMapper<${ClassName}> {

}
<#elseif Configuration.dependencies.jpa??><#-- jpa模式 -->
@Repository
public interface ${DaoClassName} extends JpaRepository<${ClassName}, Serializable> {

}
<#else><#-- mybatis模式 -->
@Mapper
public interface ${DaoClassName} {

    ${ClassName} get(Serializable id);

    List<${ClassName}> findAll();

    int insert(${ClassName} ${EntityName});

    int insertBatch(List<${ClassName}> ${EntityName}s);

    int update(${ClassName} ${EntityName});

    int delete(${ClassName} ${EntityName});

}
</#if>