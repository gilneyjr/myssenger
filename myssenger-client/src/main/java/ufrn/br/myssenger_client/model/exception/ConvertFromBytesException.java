package ufrn.br.myssenger_client.model.exception;

public class ConvertFromBytesException extends Exception {
	private static final long serialVersionUID = -7829318929216071133L;
	public ConvertFromBytesException(Exception e) {
		super(e);
	}
	public ConvertFromBytesException() {
		super();
	}
}