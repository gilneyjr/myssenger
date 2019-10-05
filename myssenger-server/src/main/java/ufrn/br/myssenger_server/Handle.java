package ufrn.br.myssenger_server;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import ufrn.br.myssenger_server.model.exception.UserAlreadyExistsException;

public class Handle {
	private static class User {
		public String username;
		public String password;
		public User(String username, String password) {
			this.username = username;
			this.password = password;
		}
	}
	
	private static ArrayList<User> users = new ArrayList<>();
	
	public static void handle(JSONObject obj) throws JSONException, UserAlreadyExistsException {
		if(obj.getString("type").equals("sign_up")) {
        	signUp(obj.getString("username"), obj.getString("password"));
        }
	}
	
	public static void signUp(String username, String password) throws UserAlreadyExistsException {
		for(User user : users) {
			if(user.username.equals(username))
				throw new UserAlreadyExistsException("Username already exists");
		}
		
		users.add(new User(username, password));
	}
}
