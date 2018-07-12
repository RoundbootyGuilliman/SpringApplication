package app.config;

import app.dao.UserDAO;
import app.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
@Import(SecurityConfig.class)
@ComponentScan(basePackageClasses = { UserDetailsServiceImpl.class, UserDAO.class })
public class PersistenceConfig {
	
	@Autowired
	@Bean
	public LocalSessionFactoryBean sessionFactory(DriverManagerDataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("app.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource bean = new DriverManagerDataSource();
		
		bean.setDriverClassName("oracle.jdbc.OracleDriver");
		bean.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bean.setUsername("shit");
		bean.setPassword("sql");
		
		return bean;
	}
	
	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		
		hibernateProperties.setProperty("hibernate.connection.pool_size", "10");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCachingRegionFactory");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		
		return hibernateProperties;
	}
}