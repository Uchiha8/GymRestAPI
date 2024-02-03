package com.epam.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.epam"})
public class SpringInitializer implements WebMvcConfigurer {
    @Bean
    @Scope("prototype")
    public SessionFactory sessionFactory() {
        return new MetadataSources(
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build()
        ).buildMetadata().buildSessionFactory();
    }
}
