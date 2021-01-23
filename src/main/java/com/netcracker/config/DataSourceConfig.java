package com.netcracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@PropertySource({"classpath:application.properties"})
public class DataSourceConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(System.getenv("APP_DATA_BASE_URL"));
        dataSource.setUsername(System.getenv("APP_DATA_BASE_NAME"));
        dataSource.setPassword(System.getenv("APP_DATA_BASE_PASSWORD"));
        return dataSource;
    }

}