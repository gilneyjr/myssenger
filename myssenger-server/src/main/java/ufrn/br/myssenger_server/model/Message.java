package ufrn.br.myssenger_server.model;

public class Message {
	private long chatId;
	private String message;
	private String sender;
	public Message(long chatId, String message, String sender) {
		super();
		this.chatId = chatId;
		this.message = message;
		this.sender = sender;
	}
	public long getChatId() {
		return chatId;
	}
	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
}
