package net.mothman.project.exceptions;

/**
 * 
 * @author mothman88
 *
 */
public class FileSystemException extends ServiceException {

	private static final long serialVersionUID = 8269608148007765868L;

	public FileSystemException() {
		super();
	}

	public FileSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSystemException(String message) {
		super(message);
	}

	public FileSystemException(Throwable cause) {
		super(cause);
	}

}
