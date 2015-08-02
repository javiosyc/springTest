package mockito.profiles;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(profiles = { "dev" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mockito/profiles/applicationContext.xml")
public class TestMyClass {

}
