package ufrn.br.myssenger_client.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 65695292432299599L;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
