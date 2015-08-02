package mockito.jndilookup;

import static org.junit.Assert.*;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mockito/jndilookup/applicationContext.xml", initializers = DataSourceTest.MockJeeLookUpInitializer.class)
public class DataSourceTest {
	@Autowired
	ApplicationContext context;

	@Test
	public void jndiResource() throws Exception {
		//assertNull(context.getBean("common-Datasource"));
		
		assertTrue(context.getBean("common-Datasource")!=null);
		
		javax.sql.DataSource ds = (javax.sql.DataSource)context.getBean("common-Datasource");
		
		System.out.println(ds);
				
	}

	public static class MockJeeLookUpInitializer
			implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {

			DataSource mockDataSource = (javax.sql.DataSource) Mockito.mock(javax.sql.DataSource.class);

			SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();

			builder.bind("java:comp/env/Datasource", mockDataSource);

			try {
				builder.activate();
			} catch (IllegalStateException | NamingException e) {
				e.printStackTrace();
			}
		}
	}

}
