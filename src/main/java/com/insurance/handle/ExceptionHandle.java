package com.insurance.handle;

import com.insurance.exception.InsuranceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insurance.model.Result;
import com.insurance.util.ResultReturn;
import com.insurance.exception.ExceptionType;

@ControllerAdvice
public class ExceptionHandle
{
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Result handel(Exception e)
	{
		if(e instanceof InsuranceException)
		{
			InsuranceException insuranceException=(InsuranceException)e;
			return ResultReturn.error(
					insuranceException.getCode(),
					insuranceException.getMessage()
			);
		}
		else
		{
			e.printStackTrace();
			
			return ResultReturn.error(
					ExceptionType.UNKNOWN_ERROR.getCode(),
					ExceptionType.UNKNOWN_ERROR.getMsg()
			);
		}

	}
}
