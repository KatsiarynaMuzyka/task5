package by.tc.nb.bean;

import by.tc.nb.bean.entity.User;

public class LoginationResponce extends Response {

	private User user;

	public LoginationResponce() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
