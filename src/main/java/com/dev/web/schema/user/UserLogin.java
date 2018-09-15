package com.dev.web.schema.user;

import com.dev.web.schema.CommonResult;

public class UserLogin extends CommonResult{
	
	private User session;
	
	public User getSession() {
		return session;
	}

	public void setSession(User session) {
		this.session = session;
	}



	public class User{
		private String adminId;
		private String shopId;
		private String password;
		private String rights;
		private String adminType;
		private String language;
		private String time;
		public String getAdminId() {
			return adminId;
		}
		public void setAdminId(String adminId) {
			this.adminId = adminId;
		}
		public String getShopId() {
			return shopId;
		}
		public void setShopId(String shopId) {
			this.shopId = shopId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRights() {
			return rights;
		}
		public void setRights(String rights) {
			this.rights = rights;
		}
		public String getAdminType() {
			return adminType;
		}
		public void setAdminType(String adminType) {
			this.adminType = adminType;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
	}
}
