package com.project.homeservicesystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.project.homeservicesystem")
@PropertySource("classpath:database.properties")
@Import({HibernateConfig.class})
public class Config {

}
