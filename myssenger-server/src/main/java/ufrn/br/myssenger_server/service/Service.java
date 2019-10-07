package ufrn.br.myssenger_server.service;

import java.util.ArrayList;
import java.util.List;

import ufrn.br.myssenger_server.model.Chat;
import ufrn.br.myssenger_server.model.Message;
import ufrn.br.myssenger_server.model.User;
import ufrn.br.myssenger_server.model.exception.InvalidChatIdException;
import ufrn.br.myssenger_server.model.exception.InvalidCredentialsException;
import ufrn.br.myssenger_server.model.exception.InvalidTokenException;
import ufrn.br.myssenger_server.model.exception.UserAlreadyExistsException;
import ufrn.br.myssenger_server.model.exception.UserDontExistException;

public class Service implements IService {
	private static ArrayList<User> users = new ArrayList<>(); // REMOVE AFTER
	
	@Override
	public void signUp(String username, String password) throws UserAlreadyExistsException {
		for(User user : users) {
			if(user.getUsername().equals(username))
				throw new UserAlreadyExistsException("Username already exists");
		}
		users.add(new User(username, password));
	}	
	
	@Override
	public String signIn(String username, String password) throws InvalidCredentialsException {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password))
					return "MyGeneratedToken";
				else
					break;
			}
		}
		throw new InvalidCredentialsException("Credentials are invalid");
	}

	@Override
	public List<Chat> getChats(String token) throws InvalidTokenException {
		List<Chat> chats = new ArrayList<>();
		for(int i = 0; i < 5; i++)
			chats.add(new Chat("friend "+ i, 0));
		
		return chats;
	}

	@Override
	public List<Message> getMessagesFromChat(String token, long chatId)
			throws InvalidTokenException, InvalidChatIdException {
		List<Message> msgs = new ArrayList<>();
		for(int i = 0; i < 5; i++)
			msgs.add(new Message(1, "Eai man " + i, "friend 0"));
		
		return msgs;
	}

	@Override
	public void newChat(String token, String friendName) throws InvalidTokenException, UserDontExistException {
		/* Nothing means Ok */
	}

	@Override
	public void newMessage(String token, long chatId) throws InvalidTokenException, InvalidChatIdException {
		/* Nothing means Ok */
	}
}
