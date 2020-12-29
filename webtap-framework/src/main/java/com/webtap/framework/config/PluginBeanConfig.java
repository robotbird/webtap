package com.webtap.framework.config;

import com.gitee.starblues.extension.resources.StaticResourceExtension;
import com.webtap.framework.quartz.QuartzJobManager;
import com.gitee.starblues.integration.application.DefaultPluginApplication;
import com.gitee.starblues.integration.application.PluginApplication;
import com.gitee.starblues.integration.application.AutoPluginApplication;
import org.quartz.SchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 插件集成配置
 *
 * @author zhangzhuo
 * @version 1.0
 */
@Configuration
public class PluginBeanConfig {


    /**
     * 定义插件应用。使用可以注入它操作插件。
     * @return PluginApplication
     */
    @Bean
    public PluginApplication pluginApplication(){
        // 实例化自动初始化插件的PluginApplication
        PluginApplication pluginApplication = new AutoPluginApplication();
//        pluginApplication.addExtension(new SpringBootMybatisExtension(
//                SpringBootMybatisExtension.Type.MYBATIS
//        ));
        // 新增静态资源扩展
        StaticResourceExtension staticResourceExtension = new StaticResourceExtension();
        staticResourceExtension.setPathPrefix("static");
//        staticResourceExtension.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        pluginApplication.addExtension(staticResourceExtension);
        return pluginApplication;
    }
}