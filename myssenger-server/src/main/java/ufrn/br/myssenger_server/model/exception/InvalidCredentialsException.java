package ufrn.br.myssenger_server.model.exception;

public class InvalidCredentialsException extends Exception {
	private static final long serialVersionUID = -5245125644010317359L;
	public InvalidCredentialsException() {
		super();
	}
	public InvalidCredentialsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidCredentialsException(String message) {
		super(message);
	}
	public InvalidCredentialsException(Throwable cause) {
		super(cause);
	}
}