package servlet.Model;

public class Users {
	private String name;
	private String password;
	
	public Users (String nume, String password) {
		this.name = nume;
		this.setPassword(password);
	}
	public Users() {
		
	}
	public String getName() {
		return name;
	}
	public void SetName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
	    return "Users{" + "userame=" + name + ", password=" + password +'}';
	}
}
