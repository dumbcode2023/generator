package com.greedystar.generator.task.project;

import com.greedystar.generator.context.DomainContext;
import com.greedystar.generator.context.ProjectContext;
import com.greedystar.generator.task.base.AbstractTask;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.TemplateUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.greedystar.generator.entity.Dependency.ALIAS_KNIFE4J;
import static com.greedystar.generator.utils.StringUtil.package2Path;
import static java.io.File.separator;

/**
 * 项目框架
 */
public class ProjectStructureTask extends AbstractTask {

    private DomainContext domainContext;

    public ProjectStructureTask(DomainContext domainContext) {
        this.domainContext = domainContext;
    }

    @Override
    public void run() throws IOException, TemplateException {
        //1. 生成pom
        Map<String, Object> data = new HashMap<>();
        ProjectContext projectContext = domainContext.getProjectCtx();
        data.put("Configuration", projectContext);
        String filePath = projectContext.getProjectPath() + projectContext.getArtifactId() + separator;

        // 生成文件
        render(data, "structure/pom.ftl", new File(filePath, "pom.xml"));

        //2. 生成yml配置
        String resourcesPath = FileUtil.getResourcePath();
        render(data, "structure/application-yml.ftl", new File(resourcesPath, "application.yml"));

        //3. 生成swagger
        String rootPath = FileUtil.getSourcePath() + package2Path(projectContext.getPackageName()) + separator;
        render(data, "structure/DockApi.ftl", new File(rootPath, "SwaggerConfig.java"));

        //4. 生成Main class
        render(data, "structure/Application.ftl", new File(rootPath, "Application.java"));

        //5. 生成统一异常处理
        render(data, "structure/BusinessException.ftl", new File(rootPath, "BusinessException.java"));

        render(data, "structure/CommonExtHandler.ftl", new File(rootPath, "CommonExtHandler.java"));

        render(data, "structure/Response.ftl", new File(rootPath, "Response.java"));

        if (projectContext.getDependency(ALIAS_KNIFE4J) != null) {
            System.out.println("生成工程结束，api接口页面访问：http://localhost:port/doc.html");
        } else {
            System.out.println("生成工程结束，api接口页面访问：http://localhost:port/swagger-ui/index.html");
        }
    }

    private void render(Map<String, Object> data, String name, File file) throws IOException {
        TemplateUtil.render(data, TemplateUtil.getInstance().getTemplate(name), file);
    }

}
