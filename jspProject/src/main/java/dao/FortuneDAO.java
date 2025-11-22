package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.FortuneRecord;
import model.UserProfile;
import util.DBConnection;

public class FortuneDAO {

	// 모든 사용자 기록 가져오기 (관리자용)
	public List<FortuneRecord> getAllRecords() throws Exception {
		String query = "SELECT fr.id, fr.fortune, fr.created_at, "
				+ "up.user_id, up.name, up.gender, up.birth_date, up.calendar_type, up.birth_time "
				+ "FROM FortuneRecords fr " + "JOIN UserProfile up ON fr.user_id = up.user_id";

		List<FortuneRecord> records = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				// UserProfile 객체 생성
				UserProfile userProfile = new UserProfile(rs.getInt("user_id"), rs.getString("name"),
						rs.getString("gender"), rs.getString("birth_date"), rs.getString("calendar_type"),
						rs.getString("birth_time"));

				// FortuneRecord 객체 생성
				FortuneRecord record = new FortuneRecord(rs.getInt("id"), userProfile, rs.getString("fortune"),
						rs.getTimestamp("created_at").toLocalDateTime());

				records.add(record);
			}
		}
		return records;
	}

	// 운세 저장
	public int saveFortune(int userId, String fortune) throws Exception {
		String query = "INSERT INTO FortuneRecords (user_id, fortune, created_at) VALUES (?, ?, ?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, userId);
			stmt.setString(2, fortune);
			stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			stmt.executeUpdate();

			// 생성된 ID 반환
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		return -1; // 실패 시 -1 반환
	}

	// 특정 사용자 기록 가져오기
	public List<FortuneRecord> getRecordsByUserId(int userId) throws Exception {
		String query = "SELECT fr.id, fr.fortune, fr.created_at, "
				+ "up.user_id, up.name, up.gender, up.birth_date, up.calendar_type, up.birth_time "
				+ "FROM FortuneRecords fr " + "JOIN UserProfile up ON fr.user_id = up.user_id "
				+ "WHERE fr.user_id = ?";

		List<FortuneRecord> records = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					// UserProfile 객체 생성
					UserProfile userProfile = new UserProfile(rs.getInt("user_id"), rs.getString("name"),
							rs.getString("gender"), rs.getString("birth_date"), rs.getString("calendar_type"),
							rs.getString("birth_time"));

					// FortuneRecord 객체 생성
					FortuneRecord record = new FortuneRecord(rs.getInt("id"), userProfile, rs.getString("fortune"),
							rs.getTimestamp("created_at").toLocalDateTime());

					records.add(record);
				}
			}
		}
		return records;
	}

	// 특정 기록 삭제
	public void deleteRecordById(int recordId) throws Exception {
		String query = "DELETE FROM FortuneRecords WHERE id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, recordId);
			stmt.executeUpdate();
		}
	}

	// 특정 사용자의 모든 운세 기록 삭제 메서드 추가
	public void deleteRecordsByUserId(int userId) throws Exception {
		String query = "DELETE FROM FortuneRecords WHERE user_id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		}
	}
}
