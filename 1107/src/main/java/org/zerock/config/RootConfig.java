package org.zerock.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages= {"org.zerock.service"})
@MapperScan(basePackages= {"org.zerock.mapper"})
@ComponentScan(basePackages= {"org.zerock.aop"})
@EnableAspectJAutoProxy
@EnableScheduling
public class RootConfig {
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public SqlSessionFactory SqlSessionFactory()throws Exception {
		
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		
		factory.setDataSource(dataSource());
		
		return factory.getObject();
	}

	@Bean
	public DataSource dataSource() {
		
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); //6버전 부터 cj
		config.setJdbcUrl("jdbc:mysql://10.10.10.105:3306/zziggu?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true");
		config.setUsername("zziggu");
		config.setPassword("zziggu");
		
		HikariDataSource ds = new HikariDataSource(config);
		
		return ds;
	}
	
}
