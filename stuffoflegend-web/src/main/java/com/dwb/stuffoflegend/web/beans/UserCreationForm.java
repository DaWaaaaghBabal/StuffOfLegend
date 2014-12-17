package com.dwb.stuffoflegend.web.beans;

public class UserCreationForm {

	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_CONFIRM = "passwordConfirm";
	public static final String EMAIL = "email";
	
	private String login;
	private String email;
	private String password;
	private String passwordConfirm;

	public boolean passwordIsConfirmed() {
		if(passwordConfirm == null || password == null)
			return false;
		return passwordConfirm.equals(password) && password.trim().length() > 0;
	}
	
	public UserCreationForm(String login, String email, String password,
			String passwordConfirm) {
		super();
		this.login = login;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
