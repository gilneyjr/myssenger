package ufrn.br.myssenger_server.model.exception;

public class UserAlreadyExistsException extends Exception {
	private static final long serialVersionUID = -2665799462292182553L;
	public UserAlreadyExistsException() {
		super();
	}
	public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	public UserAlreadyExistsException(String message) {
		super(message);
	}
	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
