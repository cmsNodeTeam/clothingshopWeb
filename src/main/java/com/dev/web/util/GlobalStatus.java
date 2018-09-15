package com.dev.web.util;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import com.dev.web.schema.user.UserSession;

public class GlobalStatus {
	public static boolean isPermission(int rights) {
		return isPermission(String.valueOf(rights));
	}

	public static boolean isPermission(String rights) {
		if (getUserSession().isSupervisor()) {
			return true;
		}
		return getUserSession().getRights().indexOf(rights) > -1 ? true : false;
	}

	public static UserSession getUserSession() {
		return ServiceReferenceContext.getUserSession();
	}

	public static Object getBody(HttpServletRequest request) {
		Object obj;
		try {
			obj = JsonUtils.getMapper().readValue(request.getInputStream(), LinkedHashMap.class);
		} catch (IOException e) {
			obj = new LinkedHashMap<>();
		}
		return obj;
	}
	
	public static String getRemoteUrl(HttpServletRequest request) {
		return request.getRequestURI().replace(request.getContextPath(), "");
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
}
