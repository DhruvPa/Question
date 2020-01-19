package exception;

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8321465096691417034L;

	String Message;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public CustomException() {
		super();
	}

	public CustomException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public CustomException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public CustomException(String arg0) {
		super(arg0);
		
	}

	public CustomException(Throwable arg0) {
		super(arg0);
		
	}

	
	
	
	
	
}
