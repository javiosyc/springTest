package mockito.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mockito/listener/applicationContext.xml")

// If a custom TestExecutionListener class is registered via
// @TestExecutionListeners, the default listeners will not be registered.
@TestExecutionListeners({ SysOutTestExecutionListener.class })

public class TestExecutionListenerTest {
	@Test
	public void someTest() throws Exception {
		System.out.println("executing someTest");
		throw new RuntimeException();
	}
}
