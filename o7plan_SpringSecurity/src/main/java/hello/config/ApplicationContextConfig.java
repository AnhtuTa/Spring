package hello.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import hello.dao.UserInfoDAO;
import hello.dao.impl.UserInfoDAOImpl;

@Configuration
@ComponentScan("hello")
@EnableTransactionManagement
@PropertySource("classpath:datasource-cfg.properties") // Load to Environment.
public class ApplicationContextConfig {
	// Lưu trữ các giá thuộc tính load bởi @PropertySource.
	@Autowired
	private Environment env;
	
	// for what???
	//@Autowired
	//private UserInfoDAO userInfoDAO;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// Xem: datasouce-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		System.out.println("## getDataSource: " + dataSource);

		return dataSource;
	}

	// Transaction Manager
	@Autowired
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		// Load property in message/validator.properties
		rb.setBasenames(new String[] { "messages/validator" });
		return rb;
	}
	
	/*
	 * CHÚ Ý: Nếu chỉ có 1 class implement thằng UserInfoDAO thì 
	 * ta ko cần định nghĩa bean này, bởi vì trong class MyDBAuthenticationService
	 * đã có 1 thuộc tính UserInfoDAO userInfoDAO; được annotate là @Autowired,
	 * do đó Spring sẽ tự tạo 1 bean kiểu UserInfoDAO, nhưng vì UserInfoDAO là
	 * 1 interface nên Spring sẽ tự động scan và tìm 1 class thực thi interface đó
	 * Vậy: nếu chỉ có 1 class implement thằng UserInfoDAO thì Spring sẽ tìm thấy
	 * nó và mọi chuyện sẽ OK!
	 * Nhưng nếu có nhiều thằng implement thằng UserInfoDAO thì cần định nghĩa bean này!
	 */
	@Bean(name="userInfoDAO")
	public UserInfoDAO getUID() {
		return new UserInfoDAOImpl(getDataSource());
	}
}
