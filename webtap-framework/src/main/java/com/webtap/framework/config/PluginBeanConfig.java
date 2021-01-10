package com.webtap.framework.config;

import com.gitee.starblues.integration.application.AutoPluginApplication;
import com.gitee.starblues.integration.application.PluginApplication;
import org.quartz.SchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 插件集成配置
 *
 * @author starBlues
 * @version 1.0
 */
@Configuration
public class PluginBeanConfig {


    /**
     * 定义插件应用。使用可以注入它操作插件。
     * @return PluginApplication
     */
    @Bean
    public PluginApplication pluginApplication(PluginListener pluginListener,
                                               SchedulerFactory schedulerFactory){
        AutoPluginApplication autoPluginApplication = new AutoPluginApplication();
        autoPluginApplication.setPluginInitializerListener(pluginListener);
        autoPluginApplication.addListener(ExamplePluginListener.class);
        return autoPluginApplication;
    }

}
