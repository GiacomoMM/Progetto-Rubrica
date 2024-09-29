
public class Utente {
	private String username;
	private String password;
	
	public Utente(String u,String p) {
		this.username=u;
		this.password=p;
	}
	
	public void setUsername(String u) {
		this.username=u;
	}
	
	public void setPassword(String p) {
		this.password=p;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
