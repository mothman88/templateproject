package com.nttdata.skyondemand.test;

import javax.sql.DataSource;

import net.mothman.project.service.EmailAlertService;
import net.mothman.project.service.FreemarkerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource({"classpath:conf.properties", "classpath:queries.properties"})
@ComponentScan(basePackages="net.mothman.project")
public class TestConfiguration {
	
	@Autowired
	Environment env;

	@Bean
	public EmailAlertService emailService() {
		return new EmailAlertService(
			 env.getProperty("emailalert.from"),
			 env.getProperty("emailalert.to"),
			 env.getProperty("emailalert.host"),
			 env.getProperty("emailalert.port"),
			 env.getProperty("emailalert.disable"));
	}
	
	@Bean
	public FreemarkerService getFreemarker() {
		return new FreemarkerService();
	}

	@Bean(name="sqliteDS")
	public DataSource dataSourceSqlite() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver.sqlite"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url.sqlite"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.user.sqlite"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.pass.sqlite"));

		return dataSource;
	}
	
	@Bean(name="oracleDS")
	public DataSource dataSourceOracle() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver.oracle"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url.oracle"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.user.oracle"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.pass.oracle"));

		return dataSource;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
}
