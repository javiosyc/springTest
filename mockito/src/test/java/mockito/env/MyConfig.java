package mockito.env;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({ "classpath:myProp.properties" })
public class MyConfig {
	@Resource
	private Environment environment;

	@Bean(name = "message")
	public String getMessage() {
		return environment.getProperty("message");
	}
	
	@Bean(name = "message2")
	public String getMessage2() {
		return environment.getProperty("message2");
	}
	
	@Bean(name = "message3")
	public String getMessage3() {
		return environment.getProperty("message3");
	}
}