package com.dev.web.util;

import com.dev.web.schema.user.UserSession;

public class ServiceReferenceContext {
	static final ThreadLocal<UserSession> currentSession = new ThreadLocal<>();
	
	public static void setUserSession(UserSession session) {
		currentSession.set(session);
	}
	
	public static UserSession getUserSession() {
		return currentSession.get();
	}

}
