package mockito.reflection;

import java.lang.reflect.Field;

public class JavaReflectionTest {
	public static void main(String[] args)
			// Just for the ease of a throwaway test. Don't
			// do this normally!
			throws Exception {
		Other t = new Other();
		t.setStr("hixxx");

		Other.class.getDeclaredField("str");

		Field field = Other.class.getDeclaredField("str");
		field.setAccessible(true);
		Object value = field.get(t);
		System.out.println(value);

	}
}

class Other {
	private String str;

	public void setStr(String value) {
		str = value;
	}
}