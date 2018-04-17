package com.wjj.o2o.config.dao;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class sessionFactoryConfiguration {

	public static String mybatisConfigFile;

	@Value("${mybatis_config_file}")
	public void setMybatisConfigFile(String mybatisConfigFile) {
		sessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
	}

	public static String mapperPath = "mapper/*.xml";

	@Value("${mappe_path}")
	public void setMapperPath(String mapperPath) {
		sessionFactoryConfiguration.mapperPath = mapperPath;
	}

	@Value("${type_aliases_package}")
	public String typeAliasesPackage = "com.wjj.o2o.entity";
	@Autowired
	private DataSource dataSource;

	@Bean(name = "sqlSessionFactoryBean")
	public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packageSerachPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
		sessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSerachPath));
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
		return sessionFactoryBean;

	}
}
