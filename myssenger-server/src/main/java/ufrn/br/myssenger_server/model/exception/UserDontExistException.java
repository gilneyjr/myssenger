package ufrn.br.myssenger_server.model.exception;

public class UserDontExistException extends Exception {
	private static final long serialVersionUID = 1450876080326675315L;
	public UserDontExistException() {
		super();
	}
	public UserDontExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public UserDontExistException(String message, Throwable cause) {
		super(message, cause);
	}
	public UserDontExistException(String message) {
		super(message);
	}
	public UserDontExistException(Throwable cause) {
		super(cause);
	}
}
