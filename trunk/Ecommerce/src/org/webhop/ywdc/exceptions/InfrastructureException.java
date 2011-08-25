package org.webhop.ywdc.exceptions;

public class InfrastructureException extends RuntimeException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InfrastructureException(){}
	public InfrastructureException(String message, Throwable cause)
	{
		super(message, cause);
	}
	public InfrastructureException(Throwable cause)
	{
		super(cause);
	}
}
