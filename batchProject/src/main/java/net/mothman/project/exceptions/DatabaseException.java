package net.mothman.project.exceptions;

/**
 * 
 * @author mothman88
 *
 */
public class DatabaseException extends ServiceException {

	private static final long serialVersionUID = 8269608148007765868L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}

}
