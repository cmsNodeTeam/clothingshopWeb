package com.dev.web.schema.user;

import java.util.Locale;

import org.springframework.util.StringUtils;

public class UserSession {

	private String username;

	private String sessionid;// 这个字段可能存用户密码

	private String language;

	private String rights;

	private String shopid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getLanguage() {
		String lang = "CN";
		if(this.language == null) {
			return lang;
		}
		switch (this.language) {
		case "en_US":
			lang = "EN";
			break;
		}
		return lang;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRights() {
		return StringUtils.isEmpty(this.rights) ? "" : this.rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public boolean isSupervisor() {
		if (StringUtils.isEmpty(this.username) || StringUtils.isEmpty(this.rights)) {
			return false;
		}
		if (this.username.equals("SUPERVISOR") || this.rights.equals("ALL")) {
			return true;
		}
		return false;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public Locale getLocalLanguage() {
		if(this.language == null) {
			return Locale.CHINA;
		}
		String[] langArr = this.language.split("_");
		return new Locale(langArr[0], langArr[1]);
	}

	public void setLocalLanguage(Locale locale) {
		this.language = getLocaleToString(
				locale == null ? Locale.CHINA : locale);
	}

	private String getLocaleToString(Locale locale) {
		return locale.getLanguage() + "_" + locale.getCountry();
	}
}
