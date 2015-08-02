package mockito.env;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class, initializers = EnvironmentMockTest2.MockPropertyInitializer.class)
public class EnvironmentMockTest2 {
	@Autowired
	ApplicationContext context;

	@Test
	public void environment() throws Exception {
	//	assertEquals("I'm a mock", context.getBean("message"));
	//	assertEquals("I'm a mockstar2", context.getBean("message2"));
		assertEquals("I'm a mock 3", context.getBean("message3"));
	}

	public static class MockPropertyInitializer
			implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {

			MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
			
			MockPropertySource mockEnvVars = new MockPropertySource().withProperty("message", "I'm a mock")
					.withProperty("message3", "I'm a mock 3");
			
			propertySources.replace(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, mockEnvVars);
		}
	}
}