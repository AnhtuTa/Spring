package demo.controller;

public class ReturnMessage {
	private String status;
	private String error;
	
	public ReturnMessage(String status, String error) {
		super();
		this.status = status;
		this.error = error;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
