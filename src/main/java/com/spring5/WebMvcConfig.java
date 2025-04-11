package com.spring5;

import java.util.concurrent.TimeUnit;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
Key Features

RESTful API with HATEOAS: - Hypermedia as the Engine of Application State, is a key concept in REST 
        (Representational State Transfer) that dictates how clients and servers interact in a RESTful application. 
    Resources include links to related resources
    Follows REST principles
    Self-descriptive messages

Web Interface with Spring MVC:
    Traditional server-side rendering
    Thymeleaf templates for HTML generation
    Simple CRUD operations through web forms

Data Model:
    JPA entities with proper relationships
    Repository pattern for data access

Separation of Concerns:
    API endpoints separate from web interface
    Clear distinction between data model and resource representation

This implementation provides a solid foundation that can be extended with additional features like:
    Authentication and authorization
    Validation
    Advanced search capabilities
    Pagination
    Caching
    API documentation with Swagger


Here are the Maven dependencies for integrating Swagger (OpenAPI) into a Spring Boot project. 
    The recommended library is springdoc-openapi-starter-webmvc-ui.

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>

This dependency includes everything needed to expose API documentation through Swagger UI. It automatically 
    configures Swagger for your Spring Boot application, eliminating the need for manual configuration 
    in most cases. After adding this dependency and rebuilding the project, 
    the Swagger UI can be accessed at http://localhost:8080/swagger-ui.html (the port may vary depending on your configuration).

*/

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spring5"})
public class WebMvcConfig implements WebMvcConfigurer {

    /*
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
       registry.jsp("/WEB-INF/views/", ".jsp");
    }
    // */
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
        registry.addMapping("/h2-console/**");
    }    

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); // Configure your prefix
        resolver.setSuffix(".jsp");
        resolver.setOrder(0); // Set the order for resolution
        return resolver;
    }

    @Bean
    public InternalResourceViewResolver htmlViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); // Use the same prefix or a different one
        resolver.setSuffix(".html");
        resolver.setOrder(1); // Set a higher order than the previous resolver
        return resolver;
    }    
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for CSS and JS
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/", "classpath:/statics/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        registry.addResourceHandler("/tags/**").addResourceLocations("/WEB-INF/tags/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }

    @Controller
    static class FaviconController {

        @RequestMapping("favicon.ico")
        String favicon() {
            return "forward:/images/favicon.ico";
        }
    }

    @Controller
    static class ServiceWorkerController {

        @RequestMapping("sw.js")
        @ResponseBody
        void serviceWorker() {
        }
    }
}
