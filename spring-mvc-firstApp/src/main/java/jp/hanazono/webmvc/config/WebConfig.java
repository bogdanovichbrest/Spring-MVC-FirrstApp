package jp.hanazono.webmvc.config;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import jp.hanazono.webmvc.dao.PersonDAO;
import jp.hanazono.webmvc.dao.PersonDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan("jp.hanazono.webmvc")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
	}

	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;

	}

	@Bean
	public AutowiredAnnotationBeanPostProcessor postProcessor() {
		return new AutowiredAnnotationBeanPostProcessor();
	}

	@Bean(name = "datasource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:~/test");
		ds.setUsername("sa");
		ds.setPassword("");

		return ds;
	}

	@Bean(name = "personDAO")
	public PersonDAO personDAO() {
		return new PersonDAOImpl();

	}

}
