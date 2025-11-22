package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserProfile;
import util.DBConnection;

public class UserProfileDAO {
	// 모든 사용자 프로필 조회
	public List<UserProfile> getAllProfiles() throws Exception {
		String query = "SELECT user_id, name, gender, birth_date, calendar_type, birth_time FROM UserProfile";

		List<UserProfile> profiles = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				UserProfile profile = new UserProfile(rs.getInt("user_id"), rs.getString("name"),
						rs.getString("gender"), rs.getString("birth_date"), rs.getString("calendar_type"),
						rs.getString("birth_time"));
				profiles.add(profile);
			}
		}
		return profiles;
	}

	// 사용자 프로필 저장 또는 업데이트
	public void saveOrUpdateProfile(int userId, String name, String gender, String birthDate, String calendarType,
			String birthTime) throws Exception {
		String query = "INSERT INTO UserProfile (user_id, name, gender, birth_date, calendar_type, birth_time) "
				+ "VALUES (?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE name = ?, gender = ?, birth_date = ?, calendar_type = ?, birth_time = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			stmt.setString(2, name);
			stmt.setString(3, gender);
			stmt.setString(4, birthDate);
			stmt.setString(5, calendarType);
			stmt.setString(6, birthTime);

			stmt.setString(7, name);
			stmt.setString(8, gender);
			stmt.setString(9, birthDate);
			stmt.setString(10, calendarType);
			stmt.setString(11, birthTime);

			stmt.executeUpdate();
		}
	}

	// 특정 사용자 프로필 가져오기
	public UserProfile getProfileById(int userId) throws Exception {
	    String query = "SELECT user_id, name, gender, birth_date, calendar_type, birth_time "
	            + "FROM UserProfile WHERE user_id = ?";
	    try (Connection connection = DBConnection.getConnection();
	            PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, userId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new UserProfile(rs.getInt("user_id"), rs.getString("name"), rs.getString("gender"),
	                        rs.getString("birth_date"), rs.getString("calendar_type"), rs.getString("birth_time"));
	            }
	        }
	    }
	    return null; // 프로필이 없으면 null 반환
	}

	public boolean updateProfile(UserProfile profile) {
		String sql = "UPDATE UserProfile SET name = ?, gender = ?, birth_date = ?, calendar_type = ?, birth_time = ? WHERE user_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, profile.getName());
			stmt.setString(2, profile.getGender());
			stmt.setString(3, profile.getBirthDate());
			stmt.setString(4, profile.getCalendarType());
			stmt.setString(5, profile.getBirthTime());
			stmt.setInt(6, profile.getUserId());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 사용자 프로필 삭제 메서드 추가
	public void deleteProfileByUserId(int userId) throws Exception {
		String query = "DELETE FROM UserProfile WHERE user_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		}
	}
}
