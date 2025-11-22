package model;

public class UserProfile {
	private int userId;
	private String name;
	private String gender;
	private String birthDate;
	private String calendarType;
	private String birthTime;

	public UserProfile(int userId, String name, String gender, String birthDate, String calendarType,
			String birthTime) {
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.calendarType = calendarType;
		this.birthTime = birthTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getCalendarType() {
		return calendarType;
	}

	public void setCalendarType(String calendarType) {
		this.calendarType = calendarType;
	}

	public String getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}
}
