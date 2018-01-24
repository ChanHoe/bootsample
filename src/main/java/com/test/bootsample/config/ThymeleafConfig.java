package com.test.bootsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
public class ThymeleafConfig {

  // 기존 src/main/resources/templates 에서 /webapp/templates 폴더로 변경
  // 템플릿모드를 HTML5 -> LEGACYHTML5로 변경 (pom.xml 에서 nekoHtml 추가.)
  // 기존 Tag 유효성 체크가 유연해진다. (열린 태그 허용.)
  @Bean
  public TemplateResolver templateResolver() {
    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
    templateResolver.setPrefix("/templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("LEGACYHTML5");
    templateResolver.setCacheable(false);
    templateResolver.setOrder(1);
    return templateResolver;
  }
  
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    return templateEngine;
  }

  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(2);
    return viewResolver;
  }
}
