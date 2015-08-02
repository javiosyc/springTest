package mockito.reflection;

public class Secret {
	private String secret;

	public void initiate(String key) {
		this.secret = key.replaceAll("a", "z").replaceAll("i", "k");
	}
}
