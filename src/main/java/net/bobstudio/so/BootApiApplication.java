package net.bobstudio.so;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// SpringBoot 应用标识
@SpringBootApplication
public class BootApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootApiApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BootApiApplication.class, args);
//		SpringApplication.run(new String[] {
//                "classpath*:app/config/spring-*.xml",
//                "classpath*:app/config/spring-session-redis.xml",
//                "classpath*:/user/captcha.xml"
//             }, args);
	}

}
