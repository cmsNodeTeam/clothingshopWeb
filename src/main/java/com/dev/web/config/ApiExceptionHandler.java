package com.dev.web.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.web.schema.CommonCode;
import com.dev.web.schema.CommonResult;
import com.dev.web.schema.exception.ApiException;
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
			String redirect = ((RedirectException)e).getRedirectUrl();
			String contextPath = request.getContextPath();
			if(!redirect.startsWith(contextPath)) {
				redirect = contextPath + redirect;
			}
			result.setRedirectURL(redirect);
			result.setCode(CommonCode.REDIRECT);
		}else if(e instanceof ApiException) {
			result.setCode(((ApiException) e).getCode());
			result.setMsg(e.getMessage());
		}else if(e instanceof NullPointerException) {
			result.setCode(CodeEnum.ERROR_NULL.getCode());
			result.setMsg(CodeEnum.ERROR_NULL.getMsg());
		}
		e.printStackTrace();
		return result;
	}
}
