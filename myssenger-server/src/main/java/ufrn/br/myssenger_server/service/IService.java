package ufrn.br.myssenger_server.service;

import java.util.List;

import ufrn.br.myssenger_server.model.Chat;
import ufrn.br.myssenger_server.model.Message;
import ufrn.br.myssenger_server.model.exception.InvalidChatIdException;
import ufrn.br.myssenger_server.model.exception.InvalidCredentialsException;
import ufrn.br.myssenger_server.model.exception.InvalidTokenException;
import ufrn.br.myssenger_server.model.exception.UserAlreadyExistsException;
import ufrn.br.myssenger_server.model.exception.UserDontExistException;

public interface IService {
	/**
	 * Create a new user.
	 * @param username User's identifier.
	 * @param password User's password.
	 * @throws UserAlreadyExistsException
	 */
	public void signUp(String username, String password) throws UserAlreadyExistsException;
	
	/**
	 * Sign in a existent user.
	 * @param username User's identifier.
	 * @param password User's password.
	 * @return A Token to that session.
	 * @throws InvalidCredentialsException If the given username and password are invalid.
	 */
	public String signIn(String username, String password) throws InvalidCredentialsException;

	/**
	 * Return the user's chats.
	 * @param string The user's token.
	 * @return A list of user's chats.
	 * @throws InvalidTokenException If the given token are invalid. 
	 */
	public List<Chat> getChats(String token) throws InvalidTokenException;
	
	/**
	 * Return a list of messages of a given chat.
	 * @param token The user's token.
	 * @param chatId The chat identifier.
	 * @return A list of messages of a given chat. 
	 * @throws InvalidTokenException If the given token are invalid.
	 * @throws InvalidChatIdException If the given chatId are invalid.
	 */
	public List<Message> getMessagesFromChat(String token, long chatId) throws InvalidTokenException, InvalidChatIdException;
	
	/**
	 * Create a new chat.
	 * @param token The user's token.
	 * @param friendName The user with whom the conversation will start.
	 * @throws InvalidTokenException If the given token are invalid.
	 * @throws UserDontExistException If the given friendName don't exists.
	 */
	public void newChat(String token, String friendName) throws InvalidTokenException, UserDontExistException;
	
	/**
	 * Create a new message in a chat.
	 * @param token The user's token.
	 * @param chatId The chat identifier. 
	 * @throws InvalidTokenException If the given token are invalid.
	 * @throws InvalidChatIdException If the given chatId are invalid.
	 */
	public void newMessage(String token, long chatId) throws InvalidTokenException, InvalidChatIdException;
}
