package sprint4Increment;

public class SystemException extends RuntimeException {

	public SystemException() {
		// TODO Auto-generated constructor stub
	}
	
	public SystemException(SystemExceptionEnum errorType) {
			
	}

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(SystemExceptionEnum errorType, String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
		switch(errorType) {
		case User : ErrorHandler errorHandler = new ErrorHandler("User");
		errorHandler.errorResponse();
		break;
		case DB : ErrorHandler errorHandler1 = new ErrorHandler("DB");
		errorHandler1.errorResponse();
		break;
		case Security : ErrorHandler errorHandler3 = new ErrorHandler("Security");
		errorHandler3.errorResponse();
		break;
		}
		// TODO Auto-generated constructor stub
	}

}
