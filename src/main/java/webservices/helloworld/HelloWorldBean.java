package webservices.helloworld;

public class HelloWorldBean  {
	String Message;

	@Override
	public String toString() {
		return "HelloWorldBean [Message=" + Message + "]";
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public HelloWorldBean(String message) {
		super();
		this.Message = message;
	}
	

}
