package net.mothman.project.exceptions;

/**
 * 
 * @author mothman88
 *
 */
public class GenericSystemException extends ServiceException {

	private static final long serialVersionUID = 8269608148007765868L;

	public GenericSystemException() {
		super();
	}

	public GenericSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericSystemException(String message) {
		super(message);
	}

	public GenericSystemException(Throwable cause) {
		super(cause);
	}

}
