package ufrn.br.myssenger_client.model.exception;

public class ConvertToBytesException extends Exception {
	private static final long serialVersionUID = -7829318929216071133L;
	public ConvertToBytesException(Exception e) {
		super(e);
	}
	public ConvertToBytesException() {
		super();
	}
}