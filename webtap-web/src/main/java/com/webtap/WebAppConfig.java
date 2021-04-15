package com.webtap;

import com.webtap.core.interceptor.UserAuthenticationInterceptor;
import com.webtap.service.impl.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.*;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static com.webtap.utils.HtmlUtil.logger;

/**
 * 文件路径映射类
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${web.upload}")
    private String webUpload;


    private UserAuthenticationInterceptor securityInterceptor;

    @Autowired
    public WebAppConfig(UserAuthenticationInterceptor securityInterceptor) {
        super();
        this.securityInterceptor = securityInterceptor;
    }

    @Resource(name="thymeleafViewResolver")
    private ThymeleafViewResolver thymeleafViewResolver;

    @Value("${webtap.cdn}")
    private String cdn = "";


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        if (thymeleafViewResolver != null) {
            Map<String, Object> vars = new HashMap<>(1);
            vars.put("cdn", cdn);
            thymeleafViewResolver.setStaticVariables(vars);
        }
        super.configureViewResolvers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //后台登录拦截器拦截路径
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/adminlogin/**");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize(DataSize.parse("10MB"));
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("1024MB"));
        return factory.createMultipartConfig();
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String classpath = StorageServiceImpl.class.getClassLoader().getResource("").getPath();

        if(classpath.indexOf(".jar")>0) {
            File path = null;
            try {
                path = new File(ResourceUtils.getURL("classpath:").getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String upload = path.getParentFile().getParentFile().getParent() + File.separator + "upload" + File.separator;
            registry.addResourceHandler("/upload/**").addResourceLocations(upload);
            registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
            super.addResourceHandlers(registry);
        }

    }

}
