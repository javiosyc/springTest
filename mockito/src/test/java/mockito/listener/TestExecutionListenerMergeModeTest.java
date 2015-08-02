package mockito.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mockito/listener/applicationContext.xml")

// To avoid the redeclaration of all default listeners, the mergeMode attribute
// of @TestExecutionListeners can be set to MergeMode.MERGE_WITH_ DEFAULTS.
@TestExecutionListeners(listeners = { SysOutTestExecutionListener.class }, mergeMode = MergeMode.MERGE_WITH_DEFAULTS)

/*
 * @TestExecutionListeners({ SysOutTestExecutionListener.class,
 * ServletTestExecutionListener.class,
 * DependencyInjectionTestExecutionListener.class,
 * DirtiesContextTestExecutionListener.class,
 * TransactionalTestExecutionListener.class,
 * SqlScriptsTestExecutionListener.class })
 */
public class TestExecutionListenerMergeModeTest {
	@Test
	public void someTest() throws Exception {
		System.out.println("executing someTest");
		throw new RuntimeException();
	}
}
