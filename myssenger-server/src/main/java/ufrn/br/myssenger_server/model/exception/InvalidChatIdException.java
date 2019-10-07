package ufrn.br.myssenger_server.model.exception;

public class InvalidChatIdException extends Exception {
	private static final long serialVersionUID = -5245125644010317359L;
	public InvalidChatIdException() {
		super();
	}
	public InvalidChatIdException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public InvalidChatIdException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidChatIdException(String message) {
		super(message);
	}
	public InvalidChatIdException(Throwable cause) {
		super(cause);
	}
}
