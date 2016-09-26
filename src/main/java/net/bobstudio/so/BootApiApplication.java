package net.bobstudio.so;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// SpringBoot 应用标识
@SpringBootApplication
public class BootApiApplication extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BootApiApplication.class, args);
	}
	
}
