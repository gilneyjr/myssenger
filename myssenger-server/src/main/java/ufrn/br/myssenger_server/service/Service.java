package ufrn.br.myssenger_server.service;

import java.util.List;

import ufrn.br.myssenger_server.model.Chat;
import ufrn.br.myssenger_server.model.Message;
import ufrn.br.myssenger_server.model.exception.InvalidChatIdException;
import ufrn.br.myssenger_server.model.exception.InvalidCredentialsException;
import ufrn.br.myssenger_server.model.exception.InvalidTokenException;
import ufrn.br.myssenger_server.model.exception.UserAlreadyExistsException;
import ufrn.br.myssenger_server.model.exception.UserDontExistException;

public class Service implements IService {
	@Override
	public void signUp(String username, String password) throws UserAlreadyExistsException {
	
	}	
	
	@Override
	public String signIn(String username, String password) throws InvalidCredentialsException {
		
		return null;
	}

	@Override
	public List<Chat> getChats(long token) throws InvalidTokenException {
		
		return null;
	}

	@Override
	public List<Message> getMessagesFromChat(long token, long chatId)
			throws InvalidTokenException, InvalidChatIdException {
		
		return null;
	}

	@Override
	public void newChat(long token, String friendName) throws InvalidTokenException, UserDontExistException {
		
	}

	@Override
	public void newMessage(long token, long chatId) throws InvalidTokenException, InvalidChatIdException {
		
	}
}
