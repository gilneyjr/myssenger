package ufrn.br.myssenger_server;

import org.json.JSONException;
import org.json.JSONObject;

import ufrn.br.myssenger_server.model.exception.InvalidChatIdException;
import ufrn.br.myssenger_server.model.exception.InvalidCredentialsException;
import ufrn.br.myssenger_server.model.exception.InvalidTokenException;
import ufrn.br.myssenger_server.model.exception.UserAlreadyExistsException;
import ufrn.br.myssenger_server.model.exception.UserDontExistException;
import ufrn.br.myssenger_server.service.IService;

public class Handle {
	private IService service;
	
	public Handle(IService service) {
		this.service = service;
	}
	
	public void handle(JSONObject obj) 
			throws  JSONException, 
					UserAlreadyExistsException, 
					InvalidCredentialsException,
					InvalidTokenException,
					InvalidChatIdException,
					UserDontExistException {
		if(obj.getString("type").equals("sign_up"))
        	service.signUp(obj.getString("username"), obj.getString("password"));
		else if(obj.getString("type").equals("sign_in"))
			service.signIn(obj.getString("username"), obj.getString("password"));
		else if(obj.getString("type").equals("get_chats"))
			service.getChats(obj.getString("token")); // Modificar
		else if(obj.getString("type").equals("get_msgs"))
			service.getMessagesFromChat(obj.getString("token"), obj.getLong("chatId")); // Modificar por causa do retorno
		else if(obj.getString("type").equals("new_chat"))
			service.newChat(obj.getString("token"), obj.getString("friend"));
		else if(obj.getString("type").equals("new_msg"))
			service.getMessagesFromChat(obj.getString("token"), obj.getLong("chatId")); // Modificar por causa do retorno
		else /* Throws an Exception */;
	}
}
