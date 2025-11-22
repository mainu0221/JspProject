package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import model.UserProfile;
import util.DBConnection;

public class UserDAO {

	public void registerUser(String loginId, String password) throws Exception {
		String query = "INSERT INTO UserLogin (login_id, password) VALUES (?, ?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, loginId);
			stmt.setString(2, password);
			stmt.executeUpdate();
		}
	}

	public User authenticateUser(String loginId, String password) throws Exception {
		String query = "SELECT id, login_id FROM UserLogin WHERE login_id = ? AND password = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, loginId);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getInt("id"), rs.getString("login_id"));
				}
			}
		}
		return null;
	}

	public User getUserById(int userId) throws Exception {
		String query = "SELECT id, login_id FROM UserLogin WHERE id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getInt("id"), rs.getString("login_id"));
				}
			}
		}
		return null;
	}

	public boolean doesUserExist(String loginId) throws Exception {
		String query = "SELECT 1 FROM UserLogin WHERE login_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, loginId);
			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		}
	}

	public UserProfile getProfileByUserId(int userId) throws Exception {
		String query = "SELECT name, gender, birth_date, calendar_type, birth_time "
				+ "FROM UserProfile WHERE user_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new UserProfile(userId, rs.getString("name"), rs.getString("gender"),
							rs.getString("birth_date"), rs.getString("calendar_type"), rs.getString("birth_time"));
				}
			}
		}
		return null; // 프로필이 없을 경우 null 반환
	}

	// 특정 사용자 계정 삭제 메서드 추가
	public void deleteUserById(int userId) throws Exception {
		String query = "DELETE FROM UserLogin WHERE id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		}
	}
}
