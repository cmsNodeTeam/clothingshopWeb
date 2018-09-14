package com.dev.web.schema;

public class CommonResult {
	private Integer code = CommonCode.FAILED;

	private String msg;
	
	private String redirectURL;

	public CommonResult() {
	}

	public CommonResult(String msg) {
		this(0, msg);
	}

	public CommonResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public CommonResult(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

}
