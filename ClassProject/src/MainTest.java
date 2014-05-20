import model.Role;
import model.User;


public class MainTest {

	public static void main(String[] args) {
		User u = new User(Role.SPC);
		System.out.println(u);
	}

}
