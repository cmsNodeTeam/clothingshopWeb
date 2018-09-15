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
		return language;
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
		Locale locale = Locale.CHINA;
		switch (this.language) {
			case "EN":
				locale = Locale.US;
		}
		return locale;
	}

	public void setLocalLanguage(Locale locale) {
		if (locale == null) {
			this.language = LangEnum.CN.toString();
			return;
		}
		String str = locale.getLanguage() + "_" + locale.getCountry();
		switch (str) {
			case "zh_CN":
				this.language = LangEnum.CN.toString();
				break;
			case "en_US":
				this.language = LangEnum.EN.toString();
				break;
		}
	}

}
