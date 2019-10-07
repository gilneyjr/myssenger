package ufrn.br.myssenger_server.model;

public class Chat {
	private String friendname;
	private long chatId;
	public Chat(String friendname, long chatId) {
		super();
		this.friendname = friendname;
		this.chatId = chatId;
	}
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public long getChatId() {
		return chatId;
	}
	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
}
