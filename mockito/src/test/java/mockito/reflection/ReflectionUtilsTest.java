package mockito.reflection;

import static org.junit.Assert.*;
import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

public class ReflectionUtilsTest {

	@Test
	public void private_field_access() {
		Secret secret = new Secret();
		secret.initiate("aio");

		Field secretField = ReflectionUtils.findField(Secret.class, "secret", String.class);
		
		assertNotNull(secretField);
		
		ReflectionUtils.makeAccessible(secretField);
		
		assertEquals("zko", ReflectionUtils.getField(secretField, secret));
		
		ReflectionUtils.setField(secretField, secret, "cool");
		
		assertEquals("cool", ReflectionUtils.getField(secretField, secret));
		
	}

}
