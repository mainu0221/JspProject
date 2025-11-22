package model;

public class User {
	private int id;
	private String loginId;

	public User(int id, String loginId) {
		this.id = id;
		this.loginId = loginId;
	}

	public int getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}
}
