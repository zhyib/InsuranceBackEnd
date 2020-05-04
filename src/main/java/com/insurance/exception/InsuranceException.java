package com.insurance.exception;

public class InsuranceException extends RuntimeException
{
	private int code;

	public InsuranceException(int code,String message)
	{
		super(message);
		this.code=code;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code=code;
	}
}
