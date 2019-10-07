package ufrn.br.myssenger_server.model.exception;

public class InvalidTokenException extends Exception {
	private static final long serialVersionUID = -5245125644010317359L;
	public InvalidTokenException() {
		super();
	}
	public InvalidTokenException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidTokenException(String message) {
		super(message);
	}
	public InvalidTokenException(Throwable cause) {
		super(cause);
	}
}
