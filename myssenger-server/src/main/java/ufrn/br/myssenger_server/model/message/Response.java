package ufrn.br.myssenger_server.model.message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Response implements Serializable {
	private boolean ok;
	private String message;
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Response(boolean ok, String message) {
		super();
		this.ok = ok;
		this.message = message;
	}
}
