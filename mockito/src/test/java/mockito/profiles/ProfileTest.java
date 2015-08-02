package mockito.profiles;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class ProfileTest extends TestMyClass{
	@Autowired
	ApplicationContext context;

	@Test
	public void profile() throws Exception {
		assertEquals("I'm a dev bean", context.getBean("message"));
		assertEquals("I'm a free bean", context.getBean("noProfileBean"));
	}
}
