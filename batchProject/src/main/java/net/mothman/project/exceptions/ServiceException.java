package net.mothman.project.exceptions;

/**
 * 
 * @author mothman88
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4940069177501716936L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
