package sprint4Increment;

public class ErrorHandler {
	
	private static String exception;
	
	public ErrorHandler() {};
	
	public ErrorHandler(String exception) {
		this.setException(exception);
	}
	
	public static void errorResponse() {

		if (getException().equalsIgnoreCase("User")) {
			System.out.println("User Exception - Logging error");
		} else if (getException().equalsIgnoreCase("DB")) {
			System.out.println("DataBase Exception - Sending notification to sys admin support");
		} else if (getException().equalsIgnoreCase("Security")) {
			System.out.println("Security Exception - user blocked");
		}
	}
	
	public static void errorLog() {
		System.out.println("Sending to Logger");
	}
		public static String getException() {
		return exception;
	}

	public void setException(String exception2) {
		this.exception = exception2;
	}
}
