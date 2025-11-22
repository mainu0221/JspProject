package model;

import java.time.LocalDateTime;

public class FortuneRecord {
	private int id;
	private UserProfile userProfile; // 사용자 프로필 객체
	private String fortune;
	private LocalDateTime createdAt;

	public FortuneRecord(int id, UserProfile userProfile, String fortune, LocalDateTime createdAt) {
		this.id = id;
		this.userProfile = userProfile;
		this.fortune = fortune;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getFortune() {
		return fortune;
	}

	public void setFortune(String fortune) {
		this.fortune = fortune;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
