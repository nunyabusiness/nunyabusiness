package model;

public class User {
	int key;
	String login;
	String password;
	Role role;
	
	public User(Role role){
		this.role = role;
	}
	public void setLogin(String login){
		this.login = login;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public boolean verifyCredentials(String login, String password){
		return this.login.equals(login) && this.password.equals(password);
	}
	public String getLogin(){
		return login;
	}
	public void setKey(int key){
		this.key = key;
	}
	public String toString(){
		return role.toString();
	}
	public void addRole(Role role){
		// add some other roles.
	}
}
