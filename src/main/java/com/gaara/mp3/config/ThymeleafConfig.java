package com.gaara.mp3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.net.URL;

/**
 * Created by Gaara_Xu
 * on 2019/5/25.
 */
@Configuration
@EnableWebMvc
public class ThymeleafConfig extends WebMvcAutoConfiguration {
    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public ViewResolver viewResolver(@Qualifier("templateEngine") SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCache(false);
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        URL resource = this.getClass().getClassLoader().getResource("templates/");         //这里把系统获取到的Class的path替换为源码对应的Path，这样修改的时候就可以动态刷新
        String devResource = resource.getFile().toString().replaceAll("target/classes", "src/main/resources");
        resolver.setPrefix("file:"+devResource);     //不允许缓存
                resolver.setCacheable(false);
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }
}