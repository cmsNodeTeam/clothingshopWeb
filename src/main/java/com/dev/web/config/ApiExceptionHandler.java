package com.dev.web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.web.schema.CommonCode;
import com.dev.web.schema.CommonResult;
import com.dev.web.schema.exception.RedirectException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult errorResult(Exception e,HttpServletRequest request) {
		CommonResult result = new CommonResult();
		if(e instanceof HttpRequestMethodNotSupportedException) {
			result.setCode(CodeEnum.METHOD_NOT_SUPPORTED.getCode());
			result.setMsg(e.getMessage());
		}else if(e instanceof RedirectException) {
			result.setRedirectURL(((RedirectException)e).getRedirectUrl());
			result.setCode(CommonCode.REDIRECT);
		}
		e.printStackTrace();
		return result;
	}
}