package net.mothman.project.exceptions;

/**
 * 
 * @author mothman88
 *
 */
public class WebServiceException extends ServiceException {

	private static final long serialVersionUID = 8564553189777097646L;

	public WebServiceException() {
		super();
	}

	public WebServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebServiceException(String message) {
		super(message);
	}

	public WebServiceException(Throwable cause) {
		super(cause);
	}

}
