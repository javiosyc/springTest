package mockito.env;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class EnvironmentTest {

	@Autowired
	ApplicationContext context;

	@Test
	public void environment() throws Exception {
		assertEquals("I'm the king", context.getBean("message"));
	}
}