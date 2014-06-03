
public class User {
	private int id;
	private int priority;
	private String name;
	private String email;
	
	
	public User(int id, int priority, String name, String email) {
		this.id = id;	
		this.name = name;
		this.email = email;
		this.priority = priority;
	}
	
	public int getPriority() {
		return this.priority;
	}
}
